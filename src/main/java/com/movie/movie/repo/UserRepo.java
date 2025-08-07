package com.movie.movie.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.movie.movie.entity.Role;
import com.movie.movie.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.verificationCode=?2,u.expiryTime=?3 where u.email=?1")
	public int addEmailVerivication(String email,String verificationCode,LocalDateTime expiryTime);
	
	@Query("select u.userName from User u where u.email=?1")
	Optional<String> findUserNameByEmail(String email);
	
	@Query("select u.email from User u where u.role=?1")
	Optional<String> findEmailByRole(Role role);
	
    Optional<User> findByVerificationCode(String verificationCode);

}
