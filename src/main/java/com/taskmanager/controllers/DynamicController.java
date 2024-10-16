package com.taskmanager.controllers;

import java.util.logging.Logger;

import com.taskmanager.repository.MyUserRepository;
import com.taskmanager.security.MyUserDetailService;
import com.taskmanager.service.TaskService;

@Controller
public class DynamicController {
	// Initialize a logger for the class
	Logger logger = LoggerFactory.getLogger(DynamicController.class.getName());

	@Autowired
	private MyUserDetailService myUserDetailService;

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private TaskService taskService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	public DynamicController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/admin/home")
	public String handleAdminHome() {

		return "home_admin";
	}

	// EndpoLong: User accessible home page
	@GetMapping("/user/home")
	public String handleUserHome() {

		logger.trace("ENTERED……………………………………handleUserHome()");
		logger.trace("EXITED……………………………………handleUserHome()");

		return "home_user";
	}

	@GetMapping("/home")
	public String handleWelcome() {

		logger.trace("ENTERED……………………………………handleWelcome()");
		logger.trace("EXITED……………………………………handleWelcome()");
		return "home_page";
	}

	@GetMapping("/login")
	public String handleLogin() {

		logger.trace("ENTERED……………………………………handleLogin()");
		logger.trace("EXITED……………………………………handleLogin()");

		System.out.println("EndpoLong hit at /login");
		return "custom_login";
	}

}
