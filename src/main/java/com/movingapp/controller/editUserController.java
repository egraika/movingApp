package com.movingapp.controller;

import com.movingapp.dao.AuthorityRepo;
import com.movingapp.dao.LocationDao;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.Authority;
import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import com.movingapp.service.UserMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;

@Controller
public class editUserController {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMapping userMapping;

	@Autowired
	private LocationDao locationDao;

	@Autowired
	private AuthorityRepo authorityRepo;

	@Transactional
	@RequestMapping(value = "/updateUser",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public ResponseEntity<UserView> updateMyProfile(@RequestBody UserView user) {

		User foundUser = userRepo.findById(user.getId()).get();

		foundUser.setPhone(user.getPhone());
		foundUser.setEmail(user.getEmail());
		foundUser.setAuthorities(user.getAuthorities());
		foundUser.setLastName(user.getLastName());
		foundUser.setFirstName(user.getFirstName());
		foundUser.setEnabled(user.getEnabled());
		foundUser.setLocations(user.getLocations());

		userRepo.save(foundUser);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getUser",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserView> getMyProfile(@RequestParam("userid") long userid) {

		User user = userService.findById(userid);

		UserView userView = userMapping.UserToUserView(user);

		return new ResponseEntity<>(userView, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/updateProfilePicture",method = RequestMethod.POST, produces = {"image/png", "image/jpeg"} )
	@ResponseBody
	public ResponseEntity<String> updateProfilePicture(@RequestBody String picture, @RequestParam("userid") long userid) throws SQLException {

		User foundUser = userRepo.findById(userid).get();
		String[] pictureToSave;
		if(picture.contains("png")) {
			foundUser.setPicture_type("png");
			pictureToSave = picture.split("data:image/png;base64,");
		} else if (picture.contains("jpeg")) {
			foundUser.setPicture_type("jpeg");
			pictureToSave = picture.split("data:image/jpeg;base64,");
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		byte[] bytes = Base64.getDecoder().decode(pictureToSave[1]);

		foundUser.setPicture(bytes);

		userRepo.save(foundUser);

		return new ResponseEntity(pictureToSave[1], HttpStatus.OK);
	}


	@RequestMapping(value = "/getAllOtherLocations",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Location>> getAllOtherLocations(@RequestParam("userid") long userid) {

		User user = userRepo.findById(userid).get();
		List<Location> locations = locationDao.findAllNotAssignedToUser(user.getLocations());

		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	@RequestMapping(value = "/getLocations",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Location>> getLocations(@RequestParam("userid") long userid) {

		User user = userRepo.findById(userid).get();
		List<Location> locations = locationDao.findAll(user.getLocations());

		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	@Transactional
	@RequestMapping(value = "/addEmployee",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public ResponseEntity<UserView> addEmployee(@RequestBody UserView userView) {

		User user = newUserFromUserView(userView);


		Authority authority = authorityRepo.findByName(user.getAuthorities().iterator().next().getName());
		Set<Authority> authoritySet = new HashSet<Authority>();
		authoritySet.add(authority);
		user.setAuthorities(authoritySet);
		user = userRepo.save(user);

		UserView newUserView = userMapping.UserToUserView(user);
		return new ResponseEntity<>( newUserView, HttpStatus.OK);
	}

	public User newUserFromUserView(UserView userView) {

		User user = new User();
		user.setEmail(userView.getEmail());
		user.setFirstName(userView.getFirstName());
		user.setLastName(userView.getLastName());
		user.setPhone(userView.getPhone());
		user.setAuthorities(userView.getAuthorities());
		user.setCcExpirationDate(userView.getCcExpirationDate());
		user.setEnabled(userView.getEnabled());
		user.setLocations(userView.getLocations());
		user.setCreatedOn(new Date());

		return user;
	}
}

