
package com.mergeproject.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mergeproject.customexception.UserIdNotFound;
import com.mergeproject.entity.CustomUser;
import com.mergeproject.entity.User;
import com.mergeproject.repository.SecurityRepository;
import com.mergeproject.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SecurityRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 

		User byEmail = userRepository.findByEmail(username)
				.orElseThrow(() -> new UserIdNotFound("User Not exits in db!"));

		log.info("loadUserByUsername :{}", byEmail.toString());

		return new CustomUser(byEmail);
	}

}
