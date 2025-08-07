package com.movie.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.dao.MovieDAO;
import com.movie.movie.entity.Movie;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {
	
	@Autowired
	MovieDAO movieDAO;

	@PreAuthorize("hasRole('ROLE_Sub')")
	@GetMapping("/subscribertest")
	public String movieTest() {
		return "subscriber test";
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
