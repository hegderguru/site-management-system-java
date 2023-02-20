package org.gunitha.sitemanagementsystem.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class WebAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	AuthenticationProvider authenticationProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Authentication authenticate;

		authenticate = SecurityContextHolder.getContext().getAuthentication();
		String usernamePassword = new String(
				Base64.getDecoder().decode(request.getHeader("Authorization").replace("Basic ", "")),
				StandardCharsets.UTF_8);
		String username = usernamePassword.split(":")[0];
		String password = usernamePassword.split(":")[1];

		/*
		 * if ((null == authenticate || (null != authenticate &&
		 * !authenticate.isAuthenticated())) && StringUtils.hasText(username) &&
		 * StringUtils.hasText(password)) { authenticate = authenticationProvider
		 * .authenticate(new UsernamePasswordAuthenticationToken(username, password)); }
		 * 
		 * if (null != authenticate && authenticate.isAuthenticated()) {
		 * filterChain.doFilter(request, response); }
		 */
		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return Arrays.asList("/register", "/login","/application/register","/application/users/user/add").contains(request.getRequestURI());
	}

}
