package com.movie.movie.dao;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.movie.movie.entity.Movie;
import com.movie.movie.repo.MovieRepo;

import jakarta.annotation.Resource;

@Repository
public class MovieDAO {
	
	@Autowired
	MovieRepo movieRepo;
	
	
	
	public Movie addMovie(Movie movie) {
		
		return movieRepo.save(movie);
	}
	
	public List<Movie> getAllMovie(){
		return movieRepo.findAll();
	}
	
	public List<Movie> getBollywood(){
		return movieRepo.findBollywood();
	}
	
	public List<Movie> getTollywood(){
		return movieRepo.findTollywood();
	}
	
	public List<Movie> getHollywood(){
		return movieRepo.findHollywood();
	}
	
	//to retrive task object based on primary key  id
	public Movie findMovie(int id) {
		
		Optional<Movie> opt=movieRepo.findById( id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public String deleteMovie(int id) {
		
		Movie movie=findMovie(id);
		
		if(movie!=null) {
			movieRepo.delete(movie);
			
			return "true";
		}else {
			return "false";
		}	
		
	}
	
	
	
	
	



	

}
