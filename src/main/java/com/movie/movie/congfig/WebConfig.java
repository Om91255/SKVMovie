package com.movie.movie.congfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
		
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // Apply CORS to your API paths
	                .allowedOrigins("http://127.0.0.1:5500","http://127.0.0.1:5501")  // The origin of your frontend
	                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed methods
	                .allowedHeaders("*");  // Allow all headers
	    }
	        
	        @Override
	        public void addViewControllers(ViewControllerRegistry registry) {
	            // Redirect "/redirectToLogin" to "/login.html"
	            registry.addRedirectViewController("/login", "/login.html");
	            registry.addRedirectViewController("/Moview", "/Movie.html");
	        }

	    
	}
