package com.taskmanager.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.dto.MyUserDto;
import com.taskmanager.model.MyUser;
import com.taskmanager.repository.MyUserRepository;
import com.taskmanager.security.MyUserDetailService;
import com.taskmanager.service.MyUserService;

@RestController
public class UserController {

	// The AuthenticationManager will help us authenticate by username and password

	// Initialize a logger for the class
	Logger logger = LoggerFactory.getLogger(UserController.class.getName());

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	MyUserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserController(MyUserService userService) {
		this.userService = userService;
	}

	@DeleteMapping("/admin/user/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws NotFoundException {
		logger.trace("ENTERED……………………………………deleteUser()");

		userService.deleteMyUserById(id);
		logger.trace("EXITED……………………………………deleteUser()");
		return new ResponseEntity<>("User found and deleted", HttpStatus.OK);
	}

	@GetMapping("/admin/activeuser")
	public ResponseEntity<MyUserDto> getActiveUser() {
		logger.trace("ENTERED……………………………………getActiveUser()");

		logger.trace("EXITED……………………………………getActiveUser()");
		return new ResponseEntity<>(userService.currentUser(), HttpStatus.OK);
	}

	@GetMapping("/admin/getallusers")
	public ResponseEntity<List<MyUser>> getAllUsers() {
		logger.trace("ENTERED……………………………………getAllUsers()");

		List<MyUser> users = myUserRepository.findAll();
		logger.trace("EXITED……………………………………getAllUsers()");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/admin/user/{id}")
	public ResponseEntity<MyUserDto> getUserDetail(@PathVariable("id") int id) {

		logger.trace("ENTERED……………………………………getUserDetail()");
		logger.trace("EXITED……………………………………getUserDetail()");
		return new ResponseEntity<>(userService.getMyUserById(id), HttpStatus.OK);
	}

	@PutMapping("/admin/userdetail/update/{id}")
	public ResponseEntity<MyUserDto> updateUserDetail(@RequestBody MyUserDto myUserUpdate, @PathVariable("id") int id) {
		logger.trace("ENTERED……………………………………updateUserDetail()");

		MyUserDto response = userService.updateMyUserDetail(myUserUpdate, id);

		logger.trace("EXITED……………………………………updatePrLongDetail()");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
