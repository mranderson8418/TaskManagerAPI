package com.taskmanager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.taskmanager.dto.MyUserDto;
import com.taskmanager.model.MyUser;
import com.taskmanager.repository.MyUserRepository;
import com.taskmanager.security.MyUserDetailService;
import com.taskmanager.service.MyUserService;

@Controller
public class UserControllerDynamic {

	// The AuthenticationManager will help us authenticate by username and

	// Initialize a logger for the class
	Logger logger = LoggerFactory.getLogger(UserControllerDynamic.class.getName());
	@Autowired
	private MyUserService myUserService;

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

	public UserControllerDynamic(MyUserService userService) {
		this.userService = userService;
	}

	@DeleteMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable int id, @ModelAttribute Optional<MyUser> myUser,
			Model model) throws NotFoundException {

		logger.trace("ENTERED……………………………………deleteUser()");

		userService.deleteMyUserById(id);

		logger.trace("EXITED……………………………………deleteUser()");

		myUser = myUserRepository.findById(id);

		model.addAttribute("myUser", myUser);

		return "user_deleted";
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
	public ResponseEntity<MyUserDto> updateUserDetail(@RequestBody MyUserDto myUserUpdate,
			@PathVariable("id") int id) {
		logger.trace("ENTERED……………………………………updateUserDetail()");

		MyUserDto response = userService.updateMyUserDetail(myUserUpdate, id);

		logger.trace("EXITED……………………………………updatePrLongDetail()");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// -----------
	// convertMyUserDtoToMyUser
	//
	@GetMapping("/login")
	public String handleLogin() {
		System.out.println("Endpoint hit at /login");
		System.out.println("\nEntering custom_login.html---------");
		return "custom_login";
	}

	@GetMapping("/admin/userList")
	public String createObject(@ModelAttribute MyUser myUser, Model model) {
		// Validate object data if necessary
		// Save object to database

		System.out.println("@GetMapping..................../userList");
		List<MyUser> users = myUserRepository.findAll();

		List<MyUserDto> myUserDtoList = new ArrayList<>();

		MyUserDto myUserDto = myUserService.mapToDto(myUser);

		for (int i = 0; i < users.size(); i++) {

			myUserDto = myUserService.mapToDto(users.get(i));

			myUserDtoList.add(myUserDto);

		}

		model.addAttribute("users", myUserDtoList);

		return "user_list";
	}

	@GetMapping("/logout")
	public String handleLogOut(@ModelAttribute MyUser myUser, Model model) {

		model.addAttribute("username", myUser.getUsername());

		System.out.println("Endpoint hit at GET /logout---------------");

		return "logout_page";
	}

	@GetMapping("/")
	public String handleWelcome() {
		System.out.println("Endpoint hit at '/'");
		System.out.println("\nEntering home_page.html.html ------------");

		return "index";
	}

	@GetMapping("/admin/home")
	public String handleAdminHome() {
		System.out.println("Endpoint hit at /admin/home");

		System.out.println("\nEntering home_admin.html------------");
		return "home_admin";
	}

	@GetMapping("/user/home")
	public String handleUserHome() {
		System.out.println("Endpoint hit at /user/home");
		System.out.println("\nEntering home_user.html--------");
		return "home_user";
	}

}
