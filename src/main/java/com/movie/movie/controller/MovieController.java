package com.movie.movie.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.movie.movie.dao.MovieDAO;
import com.movie.movie.entity.Movie;


@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	MovieDAO movieDAO;

	@GetMapping("/movietest")
	public String movieTest() {
		return "movie test";
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
