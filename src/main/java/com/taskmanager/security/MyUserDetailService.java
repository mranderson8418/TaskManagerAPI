package com.taskmanager.security;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.taskmanager.model.MyUser;
import com.taskmanager.repository.MyUserRepository;

@Component
public class MyUserDetailService implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(MyUserDetailService.class.getName());

	@Autowired
	MyUserRepository myUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.trace("ENTERED……………………………………loadUserByUsername()");

		Optional<MyUser> myUser = myUserRepository.findByUsername(username);

		if (myUser.isPresent()) {
			// we will provide a userDetails for the user
			// We are creating a "User" instance with the MyUser "myUser" instance

			var userObj = myUser.get();

			logger.trace("EXITED……………………………………loadUserByUsername()");

			// this will build an instance of UserDetailsService
			// spring security will authenticate against this

			return User.builder().username(userObj.getUsername()).password(userObj.getPassword())
					.roles(getRoles(userObj)).build();

		} else {
			logger.trace("EXITED……………………………………loadUserByUsername()");
			throw new UsernameNotFoundException(username);
		}

	}

	//
	private String[] getRoles(MyUser myUser) {

		logger.trace("ENTERED……………………………………getRoles()");

		// if user role is empty just return "USER"
		if (myUser.getRole() == null) {

			logger.trace("EXITED……………………………………getRoles()");
			return new String[] { "USER" };

		}
		logger.trace("EXITED……………………………………getRoles()");
		// this will return a String[]
		return myUser.getRole().split(",");

	}

}
