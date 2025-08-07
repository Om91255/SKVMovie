package com.movie.movie.congfig;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.movie.movie.entity.User;
import com.movie.movie.repo.UserRepo;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(email);
		
		if(user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		CustomUserDetailsImpl userDetailsImpl=new CustomUserDetailsImpl(user);
		
		List<SimpleGrantedAuthority> authorities=user.getRole().getPrivillage().stream().map(prev->new SimpleGrantedAuthority(prev.getName())).collect(Collectors.toList());
		
		authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));
		
		userDetailsImpl.setAuthorities(authorities);
		
		return userDetailsImpl;
	}

}
