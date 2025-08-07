package com.movie.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.movie.entity.User;
import com.movie.movie.entity.UserRequest;
import com.movie.movie.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public String login(UserRequest request) {
		
		User user = userRepo.findByEmail(request.getEmail());

        if (user == null) {
            return "Invalid email"; // Email is incorrect
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return "INVALID_PASSWORD"; // Password is incorrect
        }
		
		Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			//this is for default token
//			return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		
			//this is for custom token
			return jwtService.generateToken(request.getEmail());
		
		}
		
		return null;
	}

}
