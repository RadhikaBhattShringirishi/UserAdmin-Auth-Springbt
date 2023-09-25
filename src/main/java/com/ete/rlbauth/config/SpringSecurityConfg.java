package com.ete.rlbauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfg {
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
//		uses the widely supported bcrypt algorithm to hash the passwords.
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("admin"))
				.roles("ADMIN")
				.build();
		
		
//		we have created two users(user,admin) and stored them in the InMemoryUserDetailsManager class object.
		return new InMemoryUserDetailsManager(user,admin);
		
	}
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeHttpRequests((authorize)-> {
			
			authorize.anyRequest().authenticated();
			
		})
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
		
		
		}

}
