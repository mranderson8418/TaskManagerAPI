package com.taskmanager.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.taskmanager.dto.MyUserDto;
import com.taskmanager.model.MyUser;
import com.taskmanager.repository.MyUserRepository;
import com.taskmanager.service.MyUserService;

@Controller
public class RegistrationControllerDynamic {

	@Autowired
	MyUserService myUserService;
	@Autowired
	private MyUserRepository myUserRepository;

	/*
	 * When the class has only one constructor argument, and the argument matches
	 * the field name -- then spring can automatically identify the dependency to be
	 * injected public RegistrationController(MyUserRepository myUserRepository) {
	 * this.myUserRepository = myUserRepository; }
	 */

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String handleRegisterUser(Model model) {

		model.addAttribute("myUser", new MyUserDto());

		System.out.println("\nEntering register_user_form.html-------");

		return "register_form";
	}

	@PostMapping("/register")
	// @ModelAttribute - this will bind the annotated object with the model class
	public String userRegistration(@ModelAttribute MyUserDto myUserDto, Model model) {

		// MyUser myUser = myUserService.convertMyUserDtoToMyUser(myUserDto);

		Optional<MyUser> foundUser = myUserRepository.findByUsername(myUserDto.getUsername());

		if (foundUser.isPresent()) {

			// model.addAttribute(myUser.getUsername());

			System.out.println("User exists-------------------" + myUserDto.getUsername());

			model.addAttribute("myUserDto", myUserDto);

			return "index_user_exists";

		}

		System.out.println("Username: " + myUserDto.getUsername());

		System.out.println("Password: " + myUserDto.getPassword());
		System.out.println("Role: " + myUserDto.getRole());

		MyUserDto newMyUserDto = myUserService.createUser(myUserDto);

		model.addAttribute("username", myUserDto.getUsername());

		model.addAttribute("password", myUserDto.getPassword());
		model.addAttribute("role", myUserDto.getRole());

		System.out.println("User = " + myUserDto.getUsername() + " is added to the database");

		return "welcome";
	}

}
