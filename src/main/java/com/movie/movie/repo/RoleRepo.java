package com.movie.movie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.movie.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	

}
