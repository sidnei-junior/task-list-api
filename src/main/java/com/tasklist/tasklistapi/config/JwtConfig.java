package com.tasklist.tasklistapi.config;

//import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.tasklist.tasklistapi.security.JwtAuthenticateFilter;
import com.tasklist.tasklistapi.security.JwtValidateFilter;
import com.tasklist.tasklistapi.services.UserDetailServiceImpl;

@EnableWebSecurity
public class JwtConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailServiceImpl userService;
	private final PasswordEncoder passwordEncoder;
	
	public JwtConfig(UserDetailServiceImpl userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers("/swagger-resources/**").permitAll()
		.antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
		.antMatchers(HttpMethod.GET, "/v2/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilter(new JwtAuthenticateFilter(authenticationManager()))
		.addFilter(new JwtValidateFilter(authenticationManager()))
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		
//		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		return (CorsConfigurationSource) source;
//	}
	
	
	
	
}
