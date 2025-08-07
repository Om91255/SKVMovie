package com.movie.movie.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
		
		private String userName;
		
		private String password;
		
		private String email;
		
		private String verificationCode;
		 
		private boolean isVerified;
		 
		private LocalDateTime expiryTime;
		
		private String userType;
		
		@ManyToOne
		@JoinColumn(name="role_id")
		private Role role;
		
		
		
		

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		public User(String email, String verificationCode, LocalDateTime expiryTime) {
			super();
			this.email = email;
			this.verificationCode = verificationCode;
			this.expiryTime = expiryTime;
		}



		public User(int id, String userName, String password, String email, String verificationCode, boolean isVerified,
				LocalDateTime expiryTime, Role role) {
			super();
			this.id = id;
			this.userName = userName;
			this.password = password;
			this.email = email;
			this.verificationCode = verificationCode;
			this.isVerified = isVerified;
			this.expiryTime = expiryTime;
			this.role = role;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getVerificationCode() {
			return verificationCode;
		}



		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}



		public boolean isVerified() {
			return isVerified;
		}



		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}



		public LocalDateTime getExpiryTime() {
			return expiryTime;
		}



		public void setExpiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
		}



		public Role getRole() {
			return role;
		}



		public void setRole(Role role) {
			this.role = role;
		}



		public String getUserType() {
			return userType;
		}



		public void setUserType(String userType) {
			this.userType = userType;
		}

		
		

}
