package com.movie.movie.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Movie {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(nullable = false)
	    private String title;


	    @Column
	    private String director;

	    @Column
	    private String description;
	    
	    @Column
	    private String role;

	    @Column
	    private Double rating;

	    @Column
	    private String movieName;
	    
	    @Column
	    private String pathName;
	    
	    @Column
	    private String poster;
	    
	    @Column
	    private String posterPath;

	    // Constructors
	    public Movie() {
	    }

	    public Movie(String title, String director, String description,Double rating,  String movieName) {
	        this.title = title;
	        this.director = director;
	        this.description = description;
	        this.rating = rating;
	        this.movieName=movieName;
	        
	    }
	    
	    
	    
	    @Override
		public String toString() {
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", description=" + description
					+ ", role=" + role + ", rating=" + rating + ", movieName=" + movieName + ", pathName=" + pathName
					+ ", poster=" + poster + ", posterPath=" + posterPath + "]";
		}

		@PrePersist
	    public void setPathName() {
	       
	    }

	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDirector() {
	        return director;
	    }

	    public void setDirector(String director) {
	        this.director = director;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Double getRating() {
	        return rating;
	    }

	    public void setRating(Double rating) {
	        this.rating = rating;
	    }

		public String getMovieName() {
			return movieName;
		}

		public void setMovieName(String movieName) {
			this.movieName = movieName;
		}

		public String getPathName() {
			return pathName;
		}

		public void setPathName(String pathName) {
			 // Set the pathName before persisting

	        if (this.movieName!=null && this.title.equals("Bollywood")) {  
	            this.pathName = "./BollywoodMovies/" + this.movieName+".mp4";
	        }else if (this.movieName!=null && this.title.equals("Hollywood")) {  
	            this.pathName = "./HollywoodMovies/" + this.movieName+".mp4";
	        }else if (this.movieName!=null && this.title.equals("Tollywood")) {
	        	
	            this.pathName = "./TollywoodMovies/" + this.movieName+".mp4";
	        }
			
		}

		public String getPoster() {
			return poster;
		}

		public void setPoster(String poster) {
			this.poster = poster;
		}

		public String getPosterPath() {
			return posterPath;
		}

		public void setPosterPath(String posterPath) {
			
			 if (this.poster != null) {  
		            this.posterPath = "./Poster/" + this.poster+".jpg";
		      }
			
		}
		
		

		
	   
}
