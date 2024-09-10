package com.taskmanager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.dto.TaskDto;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.repository.MyUserRepository;
import com.taskmanager.security.MyUserDetailService;
import com.taskmanager.service.TaskService;

@RequestMapping(value = "/api")
@RestController
public class TaskController {

	// The AuthenticationManager will help us authenticate by username and password

	// Initialize a logger for the class
	Logger logger = LoggerFactory.getLogger(TaskController.class.getName());

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

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@PostMapping("/task/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {

		logger.trace("ENTERED……………………………………createTask()");

		System.out.println("/task/create"); // Log the creation request
		logger.trace("EXITED……………………………………createTask()");
		return new ResponseEntity<TaskDto>(taskService.createTask(taskDto), HttpStatus.CREATED);
	}

	@DeleteMapping("/task/delete/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable("id") int id) {

		logger.trace("ENTERED……………………………………deleteTaskById()");

		taskService.deleteByTaskId(id);

		logger.trace("EXITED……………………………………deleteTaskById()");
		return new ResponseEntity<>("Successfully deleted task id = " + id, HttpStatus.OK);
	}

	@GetMapping("/getalltasks")
	public ResponseEntity<TaskResponse> getAllTasks(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

		logger.trace("ENTERED……………………………………getAllTasks()");
		logger.trace("EXITED……………………………………getAllTasks()");
		return new ResponseEntity<>(taskService.getAllTasks(pageNo, pageSize), HttpStatus.OK);
	}

	@GetMapping("/task/{id}")
	public ResponseEntity<TaskDto> getTaskDetail(@PathVariable("id") int id) {
		logger.trace("ENTERED……………………………………getTaskDetail()");
		logger.trace("EXITED……………………………………getTaskDetail()");
		return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
	}

	@PutMapping("/task/update/{id}")
	public ResponseEntity<TaskDto> updateTaskDetail(@RequestBody TaskDto taskUpdate, @PathVariable("id") int id) {
		logger.trace("ENTERED……………………………………updateTaskDetail()");

		TaskDto response = taskService.updateTask(taskUpdate, id);
		logger.trace("EXITED……………………………………updateTaskDetail()");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
