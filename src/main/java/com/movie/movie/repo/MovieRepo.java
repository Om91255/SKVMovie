package com.movie.movie.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.movie.movie.entity.Movie;


public interface MovieRepo extends JpaRepository<Movie, Integer>{

	@Query("select m from Movie m where m.movieName = ?1")
	public Movie findByMovieName(String movieName);

	@Query("select m from Movie m where m.title='Bollywood'")
	public List<Movie> findBollywood();
	
	@Query("select m from Movie m where m.title='Tollywood'")
	public List<Movie> findTollywood();
	
	@Query("select m from Movie m where m.title='Hollywood'")
	public List<Movie> findHollywood();
	
	
}
