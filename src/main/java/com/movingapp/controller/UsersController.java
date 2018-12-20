package com.movingapp.controller;

import com.google.gson.Gson;
import com.movingapp.dao.AuthorityRepo;
import com.movingapp.dao.LocationDao;
import com.movingapp.entity.Authority;
import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import com.movingapp.model.TableState;
import com.movingapp.service.UserMapping;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UsersController {

	@Autowired
	private com.movingapp.dao.UserRepo UserRepo;

	@Autowired
	AuthorityRepo authorityRepo;

	@Autowired
	LocationDao locationDao;

	@Autowired
	private UserMapping userMapping;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	@ResponseBody
	public Page<UserView> getmyAssignedMembers(@RequestParam("tableState") String state, @RequestParam("userid") Long userid) {
		Gson gson = new Gson();
		TableState tableState = gson.fromJson(state, TableState.class);
		String search = "";
		String userTypeSearch = "";
		Authority userTypeToSearch = null;
		String locationSearch = "";
		Location locationToSearch = null;
		if (tableState.getSearch().getPredicateObject() != null) {
			if (tableState.getSearch().getPredicateObject().getGlobalSearch() != null)
				search = tableState.getSearch().getPredicateObject().getGlobalSearch();

			if (tableState.getSearch().getPredicateObject().getUserTypeSearch() != null) {
				userTypeSearch = tableState.getSearch().getPredicateObject().getUserTypeSearch();
				userTypeToSearch = authorityRepo.findByName(userTypeSearch);
			}

			if (tableState.getSearch().getPredicateObject().getLocationSearch() != null) {
				locationSearch = tableState.getSearch().getPredicateObject().getLocationSearch();
				locationToSearch = locationDao.findByLocation(locationSearch);
			}
		}

		int itemsPerPage = tableState.getPagination().getNumber();
		if (itemsPerPage < 1) {
			itemsPerPage = 10;
		}
		int startingIndex = tableState.getPagination().getStart();

		int pageNumber = 0;
		if (startingIndex > 0) {
			pageNumber = Math.floorDiv(startingIndex, itemsPerPage);
		}
		// this variable is duplicate
		Pageable pageable = new PageRequest(pageNumber, itemsPerPage);
		Sort sort;
		boolean isReverse = tableState.getSort().isReverse();
		String sortColumnName = tableState.getSort().getPredicate();

		if (sortColumnName == null) {
			sortColumnName = "lastName";
			isReverse = false;
		}

		if (isReverse == false) {
			sort = new Sort(Sort.Direction.ASC, sortColumnName);
		} else {
			sort = new Sort(Sort.Direction.DESC, sortColumnName);
		}
		pageable = new PageRequest(pageNumber, itemsPerPage, sort);

		List<UserView> userViews;
		Page<UserView> pages = null;
		Page<User> users;

		Optional<User> optionalUser = UserRepo.findById(userid);
		User user =  optionalUser.get();
		Set<Location> locations = user.getLocations();

		if (userTypeSearch == "" && locationSearch == "") {
			users = UserRepo.findAll(pageable, locations, search);
		} else if(userTypeSearch != "" && locationSearch == "") {
			users = UserRepo.findByUserType(pageable, locations, userTypeToSearch, search);
		} else if(userTypeSearch == "" && locationSearch != "") {
			users = UserRepo.findByLocationFilter(pageable, locations, locationToSearch, search);
		} else {
			users = UserRepo.findByUserTypeAndLocationFilter(pageable, locations, userTypeToSearch, locationToSearch, search);
		}
		userViews = userMapping.UsersToUserViews(users.getContent());

		pages = new PageImpl<UserView>(userViews, pageable, users.getTotalElements());

		return pages;
	}

	@RequestMapping(value = "/getUsersFromAssignedLocations",method = RequestMethod.GET)
	@ResponseBody
	public List<UserView> getAllNotAssignedUsersToMove(@RequestParam("userid") long userid) {
		User user = UserRepo.findById(userid).get();
		Set<Location> locationList = user.getLocations();
		Authority authority = authorityRepo.findByName("user");
		List<User> users = UserRepo.findAllWithLocationsAndNotUser(locationList, authority);
		return userMapping.UsersToUserViews(users);
	}
}

