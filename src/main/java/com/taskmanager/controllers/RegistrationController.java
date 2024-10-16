// package com.taskmanager.controllers;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.taskmanager.dto.MyUserDto;
// import com.taskmanager.model.MyUser;
// import com.taskmanager.repository.MyUserRepository;
// import com.taskmanager.service.MyUserService;
//
// @RestController
// public class RegistrationController {
// Logger logger =
// LoggerFactory.getLogger(RegistrationController.class.getName());
//
// @Autowired
// MyUserService myUserService;
//
// @Autowired
// private MyUserRepository myUserRepository;
//
// /*
// * When the class has only one constructor argument, and the argument matches
// * the field name -- then spring can automatically identify the dependency to
// be
// * injected public RegistrationController(MyUserRepository myUserRepository) {
// * this.myUserRepository = myUserRepository; }
// */
//
// @Autowired
// PasswordEncoder passwordEncoder;
//
// @PostMapping("/register/user")
// public String registerNewUser(@ModelAttribute MyUserDto myUserDto, Model
// model) {
// logger.trace("ENTERED……………………………………registerNewUser()");
//
// MyUser myUser = myUserService.convertMyUserDtoToMyUser(myUserDto);
//
// if (myUserRepository.findByUsername(myUser.getUsername()).isPresent()) {
//
// logger.trace("EXITED……………………………………registerNewUser()");
//
// return "index_user_exists";
//
// }
//
// MyUserDto newMyUserDto = myUserService.createUser(myUserDto);
//
// model.addAttribute("myUserDto", newMyUserDto);
//
// return "welcome";
//
// }
//
// // @PostMapping("/user/login")
// // public ResponseEntity<?> registerNewUser(@RequestBody MyUserDto myUserDto)
// {
// // logger.trace("ENTERED……………………………………registerNewUser()");
// //
// // if (myUserRepository.findByUsername(myUserDto.getUsername()).isPresent())
// {
// //
// // logger.trace("EXITED……………………………………registerNewUser()");
// // return new ResponseEntity<String>("User already exist in database",
// // HttpStatus.OK);
// //
// // }
// //
// // MyUserDto newMyUserDto = myUserService.createUser(myUserDto);
// //
// // return new ResponseEntity<MyUserDto>(newMyUserDto, HttpStatus.OK);
// //
// // }
// //
//
// }
