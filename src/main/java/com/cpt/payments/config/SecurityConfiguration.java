package com.cpt.payments.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.cpt.payments.security.ExceptionHandlerFilter;
import com.cpt.payments.security.HmacFilter;
import com.cpt.payments.service.HmacSHA256Service;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	HmacSHA256Service hmacSHA256Service;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
	    .csrf(csrf -> csrf.disable())
	    
	    .authorizeHttpRequests(authorize -> authorize
	    		.anyRequest().authenticated()
	    		)
	    
	    .addFilterBefore(new ExceptionHandlerFilter(), 
	    		AuthorizationFilter.class)
	    
	    .addFilterBefore(new HmacFilter(hmacSHA256Service), 
	    		AuthorizationFilter.class)
	    
	    .sessionManagement(session -> session.sessionCreationPolicy(
	    		SessionCreationPolicy.STATELESS));

		return http.build();
	}
}