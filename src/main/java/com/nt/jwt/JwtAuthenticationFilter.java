package com.nt.jwt;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.repository.UserRepository;
import com.nt.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private final JwtService jwtService;
	
	@Autowired
	private final UserRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String username;
		
		//Check if Authorization header is present and starts with "Bearer"
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		//Extract JWT token from Header
		jwtToken = authHeader.substring(7);
		username = jwtService.extractUsername(jwtToken);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// get the user Details from database
			var userDetails = userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("User Not Found"));
			
			// Validate the token
			if(jwtService.isTokenValid(jwtToken, userDetails)) {
				List<SimpleGrantedAuthority> authorities = userDetails.getRoles()
						.stream()
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
				
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						authorities);
				
				//Set Authentication details
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//Update Security context with authentication
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
