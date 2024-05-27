package com.mergeproject.mergeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mergeproject.repository.SecurityRepository;


@Service
public class UserService {


	@Autowired
	public SecurityRepository userRepository;
	@Autowired
	public JwtService jwtService;

	@Autowired
	public PasswordEncoder encoder;

	public void postUser(com.mergeproject.entity.User user) {
		user.setPassword(encoder.encode(user.getPassword()));

		System.out.println(user);
		userRepository.save(user);
	}

	public com.mergeproject.entity.User getUser(String email) {
		
		com.mergeproject.entity.User byEmail = userRepository.findByEmail(email).orElseThrow(() -> new com.mergeproject.customexception.UserIdNotFound("Id not found...!"));

		// System.out.println("get user :"+byEmail);
		return byEmail;
	}

	public String generateToken(com.mergeproject.entity.UserCradentials userDetails) {
		com.mergeproject.entity.User user = getUser(userDetails.getUserName());

		// System.out.println(user);

		return jwtService.generateToken(userDetails.getUserName(), user.getRole());

	}
}
