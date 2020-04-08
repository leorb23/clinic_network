package com.clinicnetwork.catalogmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	public void GlobalConfigure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{		
	        http
	         .csrf().disable().exceptionHandling().accessDeniedHandler(null).and().authorizeRequests()
	         .antMatchers("/" , "/login").permitAll()
	         .antMatchers("/api/**").authenticated()
	         .and()
	         .httpBasic();	    
	}
			
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{		
		return new BCryptPasswordEncoder();
	}	
		
}
