package com.tasklist.tasklistapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasklist.tasklistapi.data.UserDetailData;
import com.tasklist.tasklistapi.models.User;

public class JwtAuthenticateFilter extends UsernamePasswordAuthenticationFilter{

	public static final int TOKEN_EXPIRES = 600000;
	public static final String TOKEN_PASSWORD = "83192e42-8d8b-42c8-948e-04dbf24a42ab";
	
	private final AuthenticationManager authenticationManager;
	
	public JwtAuthenticateFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
		} catch(IOException e) {
			throw new RuntimeException("Falha ao autenticar usuário", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDetailData userData = (UserDetailData) authResult.getPrincipal();
		String token = JWT.create().
				withSubject(userData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
	
	
	
	
}
