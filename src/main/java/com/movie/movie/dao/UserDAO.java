package com.movie.movie.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.movie.movie.entity.Role;
import com.movie.movie.entity.User;
import com.movie.movie.repo.RoleRepo;
import com.movie.movie.repo.UserRepo;

@Repository
public class UserDAO {
	
	@Autowired
	public UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
/*---------------------------------------------------------------------------------*/
	
	//for adding new user
	public String addUser(String userName,String password,String email) {
		
		System.out.println(userName+" "+password+" "+ email);	
		
		User user=userRepo.findByEmail(email);
		
		
		if(user!=null) {
			
			Optional<String> name=userRepo.findUserNameByEmail(email);
			
			if(name.isPresent()) {
				
				return "user all ready exist !";
				
			}else {
				Role role=roleRepo.findById(2).orElseThrow(()-> new IllegalArgumentException("Role with ID " + "2" + " not found"));
				user.setUserName(userName);
				user.setEmail(email);
				user.setPassword(passwordEncoder.encode(password));
				user.setRole(role);
				
				userRepo.save(user);
				
				return "registration done successfully";
			}
			
		}else {
			return "verify email first";
		}
		
		
		
	}

/*---------------------------------------------------------------------------------*/
	
	//for get all user
	public List<User> userList(){
		return userRepo.findAll();
	}

/*---------------------------------------------------------------------------------*/

	//finding user by id 
	public User findById(int id) {
		Optional<User> user=userRepo.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		return null;
		
	}
	
/*---------------------------------------------------------------------------------*/

	//deleting user by id
	public String deleteUser(int id) {
		
		User user=findById(id);
		
		if(user!=null) {
			userRepo.delete(user);
			return "true";
		}else {
			return "false";
		}
	}
	
/*---------------------------------------------------------------------------------*/

	//for email validating for forget password
	public boolean validatEmail(String email) {
		
		User user=userRepo.findByEmail(email);
		
		if(user!=null) {
			return true;
		}
		
		return false;
	}
	
/*---------------------------------------------------------------------------------*/
	
	//for updating password (forget password)
	public boolean updatePassword(String email,String password) {
		
		User user=userRepo.findByEmail(email);
		
		if(user!=null) {
			user.setPassword(passwordEncoder.encode(password));
			userRepo.save(user);
			return true;
		}
		
		return false;
	}
}
