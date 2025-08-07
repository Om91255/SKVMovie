package com.movie.movie.congfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{
	
	@Autowired
	private CustomUserDetailsServiceImpl useDetailsServiceImpl;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(useDetailsServiceImpl);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf->csrf.disable())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(req->req.requestMatchers("/email/**","/movie/**").permitAll()
				
				.requestMatchers("/admin/**").hasRole("Admin")
				.requestMatchers(HttpMethod.GET).hasAuthority("READ")
				.requestMatchers(HttpMethod.POST).hasAuthority("CREATE")
				.requestMatchers(HttpMethod.PUT).hasAuthority("UPDATE")
				.requestMatchers(HttpMethod.DELETE).hasAuthority("DELETE")
				
				.requestMatchers("/user/**","/email/**").hasRole("User")
				
				.requestMatchers("/subscriber/**","/user/**").hasRole("Sub")
				.requestMatchers(HttpMethod.GET).hasAuthority("READ")
				)
		.httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
            
//            .csrf(csrf->csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/Bollywood","/Tollywood","/Hollywood","/MovieList").permitAll()
//                
//                .anyRequest().authenticated()
//            )
//           ;
        
          

        return http.build();
    }
	
	
	 @Bean
	 public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // Allow frontend origin
	        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
	        configuration.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
	
}
