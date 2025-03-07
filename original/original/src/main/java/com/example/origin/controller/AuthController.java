package com.example.origin.controller;

import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.origin.entity.PasswordResetToken;
import com.example.origin.entity.User;
import com.example.origin.entity.VerificationToken;
import com.example.origin.event.SignupEventPublisher;
import com.example.origin.form.PasswordEditForm;
import com.example.origin.form.SignupForm;
import com.example.origin.repository.PasswordResetTokenRepository;
import com.example.origin.service.PassService;
import com.example.origin.service.UserService;
import com.example.origin.service.VerificationTokenService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller

public class AuthController {
	
	 private final UserService userService;  
	 private final SignupEventPublisher signupEventPublisher;
	 private final VerificationTokenService verificationTokenService;
	 private final PasswordResetTokenRepository passwordResetTokenRepository;
		private final PassService passService;
		private final JavaMailSender javaMailSender;
	    
	 public AuthController(UserService userService, SignupEventPublisher signupEventPublisher, VerificationTokenService verificationTokenService
			 				,PasswordResetTokenRepository passwordResetTokenRepository,PassService passService
			 				,JavaMailSender mailSender) { 
	     
	        this.userService = userService;   
	        this.signupEventPublisher = signupEventPublisher;
	        this.verificationTokenService = verificationTokenService;
	        this.passwordResetTokenRepository = passwordResetTokenRepository;
			this.passService  = passService;
			this.javaMailSender = mailSender;
	        
	    }    
	    
	  @GetMapping("/login")
	    public String login() {
	        return "auth/login";
	    }   
	  
//	  @GetMapping("/signup")
//	    public String signup() {
//	        return "auth/signup";
//	    }   
	  
	  @GetMapping("/signup")
	    public String signup(Model model) {        
	        model.addAttribute("signupForm", new SignupForm());
	        return "auth/signup";
	    }    
	  
	  @PostMapping("/signup")
	  public String signup(@ModelAttribute @Validated SignupForm signupForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
	        // メールアドレスが登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
	        if (userService.isEmailRegistered(signupForm.getEmail())) {
	            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
	            bindingResult.addError(fieldError);                       
	        }    
	        
	        // パスワードとパスワード（確認用）の入力値が一致しなければ、BindingResultオブジェクトにエラー内容を追加する
	        if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
	            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが一致しません。");
	            bindingResult.addError(fieldError);
	        }        
	        
	        if (bindingResult.hasErrors()) {
	            return "auth/signup";
	        }
	        
	        User createdUser = userService.create(signupForm);
	        String requestUrl = new String(httpServletRequest.getRequestURL());
	        signupEventPublisher.publishSignupEvent(createdUser, requestUrl);
	        redirectAttributes.addFlashAttribute("successMessage", "ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");

	        return "redirect:/";
	    }    
	  
	  @GetMapping("/passedit")
	    public String passedit() {
	        return "auth/passedit";
	    }   
	  
	  @GetMapping("/passreset")
	    public String showForgotPasswordForm() {
	    	
	        return "/auth/passreset";
	    }
	
  @PostMapping("/passreset")
	    public String processForgotPassword(String email, RedirectAttributes redirectAttributes) {
//	    	token作成
	        String token = UUID.randomUUID().toString();
	        userService.createPasswordResetTokenForUser(email, token);

//	      リセットのためのtokenのついたURLを作成
	        String resetUrl = "http://localhost:8080/reset-password?token=" + token;
	        try {
//	        	メール送信：メール内容は、下のsendEmail()で実行
	            sendEmail(email, resetUrl);
	            redirectAttributes.addFlashAttribute("successMessage", "パスワード再発行リンクをメールで送信しました。");
	        } catch (MessagingException e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "メール送信に失敗しました。");
	        }

	        return "redirect:/auth/passreset";
	    }

	    // パスワード再発行③：パスワードを再発行のためのメール発行・送信
	    private void sendEmail(String email, String resetUrl) throws MessagingException {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        helper.setTo(email);
	        helper.setSubject("NAGOYAMESHI：パスワード再発行リンク");
	        helper.setText("以下のリンクをクリックしてパスワードを再発行してください: " + resetUrl);

	        javaMailSender.send(message);
	    }
	    
	    
	    @GetMapping("/reset-password")
	    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
	        PasswordResetToken resetToken = userService.getPasswordResetToken(token);
	        if (resetToken == null) {
	            model.addAttribute("message", "無効なトークンです。");
	            return "redirect:/login";
	        }
	        model.addAttribute("token", token);
	        return "/auth/passedit";
	    }

	    @PostMapping("/passedit")
	    public String processResetPassword(@RequestParam("token") String token,
	                                       @Valid PasswordEditForm passwordEditForm,
	                                       BindingResult result,
	                                       RedirectAttributes redirectAttributes) {
	        if (result.hasErrors()) {
//	        	result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
	            redirectAttributes.addFlashAttribute("error", "フォームにエラーがあります。");
	            return "redirect:/auth/passedit?token=" + token;
	        }
	        boolean success = userService.updatePassword(token, passwordEditForm.getNewPassword());
	        if (success) {
	            redirectAttributes.addFlashAttribute("message", "パスワードが正常に更新されました。");
	            return "redirect:/login";
	        } else {
	            redirectAttributes.addFlashAttribute("error", "パスワードの更新に失敗しました。");
	            return "redirect:/auth/passedit?token=" + token;
	        }
	    }
	  
	  @GetMapping("/signup/verify")
	    public String verify(@RequestParam(name = "token") String token, Model model) {
	        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
	        
	        if (verificationToken != null) {
	            User user = verificationToken.getUser();  
	            userService.enableUser(user);
	            String successMessage = "会員登録が完了しました。";
	            model.addAttribute("successMessage", successMessage);            
	        } else {
	            String errorMessage = "トークンが無効です。";
	            model.addAttribute("errorMessage", errorMessage);
	        }
	        
	        return "auth/verify";         
	    }    
	  
	  @GetMapping("/delete")
	    public String showDeletePage(Model model) {
	        return "auth/delete"; // auth/delete.html を表示
	    }
	  
	  @PostMapping("/delete")
	  public String deleteUser(@AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
	      if (userDetails == null) {
	          redirectAttributes.addFlashAttribute("error", "ログインしていません。");
	          return "redirect:/login";
	      }

	      try {
	          String email = userDetails.getUsername();
	          User user = userService.findByEmail(email);
	          if (user == null) {
	              redirectAttributes.addFlashAttribute("error", "ユーザー情報が見つかりません。");
	              return "redirect:/auth/delete";
	          }

	          userService.deleteUser(user.getId());
	          redirectAttributes.addFlashAttribute("message", "ユーザーが削除されました");

	          return "redirect:/";
	      } catch (Exception e) {
	          e.printStackTrace();
	          redirectAttributes.addFlashAttribute("error", "削除に失敗しました");
	          return "redirect:/auth/delete";
	      }
	  }
	  
	  @GetMapping("/privacy")
	    public String privacy() {
	        return "auth/privacy";
	    }   
	  
	  @GetMapping("/inquiry")
	    public String inquiry() {
	        return "auth/inquiry";
	    } 
	  
	    }

