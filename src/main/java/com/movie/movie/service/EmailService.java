package com.movie.movie.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.movie.entity.User;
import com.movie.movie.repo.UserRepo;

@Service
public class EmailService {
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	

	    @Autowired
	    private UserRepo userRepo;

/*---------------------------------------------------------------------------------*/

	    //for verify email and send otp
	    public void sendVerificationEmail(String email) {
	    	
	        // Generate a random 6-digit OTP
	        String otp = String.valueOf(100000 + new Random().nextInt(900000));
	        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5); // OTP valid for 5 minutes
	        
	        User user=userRepo.findByEmail(email);
	        
	        if(user==null) {
	        	user = new User(email, otp, expiryTime);
		        userRepo.save(user);
	        }else {
	        	user.setExpiryTime(expiryTime);
	        	user.setVerificationCode(otp);
	        	userRepo.save(user);
	        }
	        
	        	
	        
	        // Send email
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	message.setTo(email);
	        message.setSubject("Email Verification Code");
	    	message.setText("Your verification code is: " + otp + "\n\nThis code is valid for 5 minutes.");
	    	message.setFrom("om91255@gmail.com");

	    	mailSender.send(message);
	    	System.out.println("Verification email sent to " + email);
	        
	    }

/*---------------------------------------------------------------------------------*/

	    //for validate otp
	    public boolean verifyOtp(String email, String otp) {
	    	System.out.println(email);
	    	System.out.println(otp);
	    	User user=userRepo.findByEmail(email);
	    	
	    	if(user!=null) {
	    		
	    	    if (user.getVerificationCode() != null && user.getVerificationCode().equals(otp) 
	    	            && user.getExpiryTime().isAfter(LocalDateTime.now())) {
	    	            
	    	            // Set user as verified
	    	            user.setVerified(true);
	    	            userRepo.save(user);
	    	            return true;  // OTP is valid and user is verified
	    	        }
	    		
	    	}else {
	    		return false;
	    	}
	    	
	        return false;
	    }
	    
	

}
