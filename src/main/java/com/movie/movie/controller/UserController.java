package com.movie.movie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.dao.MovieDAO;
import com.movie.movie.dao.UserDAO;
import com.movie.movie.entity.Movie;
import com.movie.movie.entity.UserRequest;
import com.movie.movie.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	

	
	@GetMapping("/getstr")
	public String getStr() {
		return "user test";
	}
	
	
	@GetMapping("/MovieList")
	public List<Movie> allMovie(){
		return movieDAO.getAllMovie();
	}
	
	
	@GetMapping("/Bollywood")
	public List<Movie> allBollyWood(){
		return movieDAO.getBollywood();
	}

	@GetMapping("/Tollywood")
	public List<Movie> allTollywood(){
		return movieDAO.getTollywood();
	}
	
	@GetMapping("/Hollywood")
	public List<Movie> allHollywood(){
		return movieDAO.getHollywood();
	}
	
	

	

}
