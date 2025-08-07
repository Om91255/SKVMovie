package com.movie.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.movie.dao.MovieDAO;
import com.movie.movie.dao.UserDAO;
import com.movie.movie.entity.Movie;
import com.movie.movie.entity.User;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private MovieDAO movieDAO;
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping("/getAdmin")
	public String getAdmin() {
		return "admin";
	}
	
	@PostMapping("/addMovie")
	public String uploadMovie(@RequestBody Movie movie) {
		movie.setPathName("");
		movie.setPosterPath("");
		
		movieDAO.addMovie(movie);
		System.out.println(movie.toString());
		return "added";
	}
	
	@GetMapping("/MovieList")
	public List<Movie> allMovie(){
		return movieDAO.getAllMovie();
	}
	
	@DeleteMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable int id){
		return movieDAO.deleteMovie(id);
	}
	
	@GetMapping("/UserList")
	public List<User> allUser(){
		return userDAO.userList();
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id){
		return userDAO.deleteUser(id);
	}
	
}
