package com.chiu.projects.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiu.projects.configuration.JwtUserDetailsService;
import com.chiu.projects.configuration.TokenManager;
import com.chiu.projects.model.JwtRequestModel;
import com.chiu.projects.model.JwtResponseModel;

@RestController
@CrossOrigin
public class JwtController {
	private JwtUserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	private TokenManager tokenManager;
	
	public JwtController(JwtUserDetailsService userDetailsService,
						AuthenticationManager authenticationManager,
						TokenManager tokenManager) {
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping("/token")
	public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), 
					request.getPassword()));
		}
		catch(DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}
		catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String jwtToken = tokenManager.generateJwtToken(userDetails);

		JwtResponseModel jwtResponseModel = JwtResponseModel.builder()
											.token(jwtToken)
											.build();
		
		return ResponseEntity.ok(jwtResponseModel);
	}
}