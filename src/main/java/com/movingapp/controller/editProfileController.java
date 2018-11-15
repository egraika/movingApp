package com.movingapp.controller;

import com.movingapp.dao.UserRepo;
import com.movingapp.entity.User;
import com.movingapp.service.UserMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.UserView;
import com.stripe.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
public class editProfileController {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapping userMapping;

	@Transactional
	@RequestMapping(value = "/updateMyProfile",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public ResponseEntity<UserView> updateMyProfile(@RequestBody UserView user) {

		User updatedUser = userMapping.UserViewToUser(user);

		userRepo.save(updatedUser);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getMyProfile",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserView> getMyProfile(@RequestParam("userid") long userid) {

		User user = userService.findById(userid);

		UserView userView = userMapping.UserToUserView(user);

		return new ResponseEntity<>(userView, HttpStatus.OK);
	}
}

