package com.movie.movie.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.dao.UserDAO;
import com.movie.movie.entity.Role;
import com.movie.movie.entity.UserRequest;
import com.movie.movie.repo.UserRepo;
import com.movie.movie.service.EmailService;
import com.movie.movie.service.UserServiceImpl;

@RestController
@RequestMapping("/email")
public class UserEmailController {
	
		@Autowired
		private EmailService emailService;
		
		@Autowired
		private UserDAO userDao;
		
		@Autowired
		private UserRepo userRepo;

		@Autowired
		private UserServiceImpl userServiceImpl;
		
		@GetMapping("/emaitest")
		public String emailTest() {
			return "email test";
		}

/*---------------------------------------------------------------------------------*/
		
		//for generating OTP and send it to user
	  	@PostMapping("/send-verification")
	    public String sendVerification(@RequestParam String email) {
	        emailService.sendVerificationEmail(email);
	        return "Verification email sent to " + email;
	    }

/*---------------------------------------------------------------------------------*/
	  	
	  	//for validating OTP sending through user
	    @PostMapping("/verify-otp")
	    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
	    	System.out.println(email);
	    	System.out.println(otp);
	        boolean isValid = emailService.verifyOtp(email, otp);
	        return isValid ? "true" : "false";
	    }
	    
/*---------------------------------------------------------------------------------*/	    

	    //for register new user
	    @PostMapping("/register")
		public String registerUser(@RequestParam String userName,@RequestParam String password,@RequestParam String email) {
			
			System.out.println(userName+" "+password+" "+ email);
			
			String data=userDao.addUser(userName, password, email);
			
			return data;
		}
	    
/*---------------------------------------------------------------------------------*/	    
		
	    //for user login
	    @PostMapping("/login")
		public ResponseEntity<Map<String, Object>> login(@RequestBody UserRequest userRequest) {
		    Map<String, Object> response = new HashMap<>();
		    
		    String token = userServiceImpl.login(userRequest);
		    

		    if (token == null) {
		        response.put("message", "Invalid credentials");
			    return ResponseEntity.status(HttpStatus.OK).body(response);
		    }

		    if ("Invalid email".equals(token)) {
		        response.put("message", "Invalid email");
			    return ResponseEntity.status(HttpStatus.OK).body(response);
		    }

		    if ("INVALID_PASSWORD".equals(token)) {
		        response.put("message", "Invalid password");

		        return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		    
		    Role role1=new Role();
		    role1.setId(1);
		    
		    String adminEmail = userRepo.findEmailByRole(role1).orElse("Email not found");

		    
		    if(adminEmail.equals(userRequest.getEmail())) {
		    	response.put("message", "Admin");
		    	response.put("token", token);
			    return ResponseEntity.status(HttpStatus.OK).body(response);
		    }
		    

		    response.put("token", token);
		    return ResponseEntity.status(HttpStatus.OK).body(response);
		}

/*---------------------------------------------------------------------------------*/
	    
	    //for email validation for forget password
	    @PostMapping("/EmailValidation")
		public boolean validatingEmail(@RequestParam String email) {
			
			boolean vaildation=userDao.validatEmail(email);
			
			if(vaildation==true) {
				
				//if email found then send OTP to user
				emailService.sendVerificationEmail(email);
				return true;
			}
			
			return false;
		}

/*---------------------------------------------------------------------------------*/

	    @PostMapping("/newPassword")
	    public boolean newPassword(@RequestParam String email,@RequestParam String password) {
	    	
	    	boolean newPwd=userDao.updatePassword(email, password);
	    	
	    	if(newPwd==true) {
	    		return true;
	    	}
	    	
	    	return false;
	    }

}
