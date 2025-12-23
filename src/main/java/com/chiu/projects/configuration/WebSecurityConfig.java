package com.chiu.projects.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtFilter jwtFilter;
	
	public WebSecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
							JwtFilter jwtFilter) {
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtFilter = jwtFilter;
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() {
		return authentication -> new UsernamePasswordAuthenticationToken("randomuser123", "password");
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.headers(headers -> headers
									.frameOptions(FrameOptionsConfig::sameOrigin)
						)
				.authorizeHttpRequests(request -> request
														.requestMatchers("/token").permitAll()
														.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-ui/**")).permitAll()
														.requestMatchers(AntPathRequestMatcher.antMatcher("/actuator/**")).permitAll()
														.anyRequest().authenticated()
									)
				.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
									)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
