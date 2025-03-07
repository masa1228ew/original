package com.example.origin.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.origin.entity.PasswordResetToken;
import com.example.origin.entity.Role;
import com.example.origin.entity.User;
import com.example.origin.form.SignupForm;
import com.example.origin.repository.PasswordResetTokenRepository;
import com.example.origin.repository.RoleRepository;
import com.example.origin.repository.UserRepository;
import com.example.origin.repository.VerificationTokenRepository;

@Service
public class UserService {
	 private final UserRepository userRepository;
	    private final RoleRepository roleRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final PasswordResetTokenRepository passwordResetTokenRepository;
	     private final  VerificationTokenRepository verificationTokenRepository;
	    
	    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder
	    		,PasswordResetTokenRepository passwordResetTokenRepository,
	 			VerificationTokenRepository verificationTokenRepository) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;        
	        this.passwordEncoder = passwordEncoder;
	        this.passwordResetTokenRepository = passwordResetTokenRepository;
	         this.verificationTokenRepository = verificationTokenRepository;
	    }    
	    
	    @Transactional
	    public User create(SignupForm signupForm) {
	        User user = new User();
	        Role role = roleRepository.findByName("ROLE_GENERAL");
	        
	        
	        user.setEmail(signupForm.getEmail());
	        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
	        user.setRole(role);
	        user.setEnabled(false);        
	        
	        return userRepository.save(user);
	    }    
	    
	    public boolean isEmailRegistered(String email) {
	        User user = userRepository.findByEmail(email);  
	        return user != null;
}
	    
	    public boolean isSamePassword(String password, String passwordConfirmation) {
	        return password.equals(passwordConfirmation);
	    }    
	    
	    @Transactional
	    public void enableUser(User user) {
	        user.setEnabled(true); 
	        userRepository.save(user);
	    }    
	    
	    public void createPasswordResetTokenForUser(String email, String token) {
	        User user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new RuntimeException("ユーザーが見つかりません。");
	        }
	        PasswordResetToken myToken = new PasswordResetToken();
	        myToken.setToken(token);
	        myToken.setUser(user);
	        passwordResetTokenRepository.deleteByUser(user);
	        passwordResetTokenRepository.save(myToken);
	        
	      
	    }

	    public PasswordResetToken getPasswordResetToken(String token) {
	        return passwordResetTokenRepository.findByToken(token);
	    }
		
		 public boolean updatePassword(String token, String newPassword) {
		        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
		        if (resetToken == null) {
		            return false;
		        }
		       
		        User user = resetToken.getUser();
		        user.setPassword(passwordEncoder.encode(newPassword));
		        userRepository.save(user);
		        passwordResetTokenRepository.delete(resetToken);
		        return true;
		    }
		 
		 public User findByEmail(String email) {
		        User user = userRepository.findByEmail(email);
		        if (user == null) {
		            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
		        }
		        return user;
		    }
		 
		 public void deleteUser(Integer userId) {
		        userRepository.deleteById(userId);
		    }
}