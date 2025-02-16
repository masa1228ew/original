package com.example.origin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class WebSecurityConfig {
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	       
	            .authorizeHttpRequests(auth -> auth                
	            		.requestMatchers("/css/**", "/images/**", "/js/**", "/storage/**", "/", "/signup/**","/passedit/**", "/passreset/**","/reset-password/**").permitAll()  // すべてのユーザーにアクセスを許可するURL
	            		   .requestMatchers("/table/**").permitAll()
	            		   .requestMatchers(HttpMethod.DELETE, "/table/**").permitAll() 
	            		   
	            		   .requestMatchers("/delete").authenticated() // ユーザー削除は認証が必要
	                .requestMatchers("/admin/**").hasRole("ADMIN")  // 管理者にのみアクセスを許可するURL
	                .anyRequest().authenticated()  // 上記以外のURLはログインが必要（会員または管理者のどちらでもOK）
	                
	            )
	            .formLogin((form) -> form
	                .loginPage("/login")              // ログインページのURL
	                .loginProcessingUrl("/login")     // ログインフォームの送信先URL
	                .defaultSuccessUrl("/?loggedIn", true)  // ログイン成功時のリダイレクト先URL
	                .failureUrl("/login?error")       // ログイン失敗時のリダイレクト先URL
	                .permitAll()
	            )
	            .logout((logout) -> logout
	                .logoutSuccessUrl("/?loggedOut")  // ログアウト時のリダイレクト先URL
	                .permitAll()
	            );    
//	        .csrf(csrf -> csrf.disable()); // CSRFを無効化（削除リクエストがPOSTなので必要）
	            
	        return http.build();
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    
	   
}
