package com.movingapp.controller;

import com.google.gson.Gson;
import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import com.movingapp.model.MoveEntity;
import com.movingapp.model.NoteEntity;
import com.movingapp.model.TableState;
import com.movingapp.view.Move;
import com.movingapp.view.Note;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class MovesTableController {
	
	@Autowired
	private com.movingapp.dao.BookMovesDao BookMovesDao;

	@Autowired
	private com.movingapp.dao.UserRepo UserRepo;
	
	@RequestMapping(value = "/getBookedMoves", method = RequestMethod.GET)
	@ResponseBody
	public Page<Move> getmyAssignedMembers(@RequestParam("tableState") String state, @RequestParam("userid") Long userid) {
		Gson gson = new Gson();
		TableState tableState = gson.fromJson(state, TableState.class);
		String search = "";
		String statusSearch = "";
		if (tableState.getSearch().getPredicateObject() != null) {
			if (tableState.getSearch().getPredicateObject().getGlobalSearch() != null)
				search = tableState.getSearch().getPredicateObject().getGlobalSearch();
			
			if (tableState.getSearch().getPredicateObject().getStatusSearch() != null)
				statusSearch = tableState.getSearch().getPredicateObject().getStatusSearch();
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
			sortColumnName = "id";
			isReverse = false;
		}

		if (isReverse == false) {
			sort = new Sort(Sort.Direction.ASC, sortColumnName);
		} else {
			sort = new Sort(Sort.Direction.DESC, sortColumnName);
		}
		pageable = new PageRequest(pageNumber, itemsPerPage, sort);

		List<Move> moves;
		Page<Move> pages = null;
		Page<MoveEntity> moveEntities;

		Optional<User> optionalUser = UserRepo.findById(userid);
		User user =  optionalUser.get();
		Set<Location> locations = user.getLocations();
		ArrayList<String> locationsStrings = new ArrayList<String>();
		for (Location location : locations) {
			locationsStrings.add(location.getLocation());
		}
		if (search == "" && statusSearch == "") {
			moveEntities = BookMovesDao.findAll(pageable, locationsStrings.toArray());
		} else {
			moveEntities = BookMovesDao.findAll(search, statusSearch, pageable, locationsStrings.toArray());
		}
		moves = mapToMove(moveEntities.getContent());

		pages = new PageImpl<Move>(moves, pageable, moveEntities.getTotalElements());

		return pages;
	}

	@RequestMapping(value = "/getCalenderMoves", method = RequestMethod.GET)
	@ResponseBody
	public List<Move> getCalendarMoves(@RequestParam("userid") Long userid, @RequestParam("year") int year) {
		Optional<User> optionalUser = UserRepo.findById(userid);
		User user =  optionalUser.get();
		Set<Location> locations = user.getLocations();
		ArrayList<String> locationsStrings = new ArrayList<String>();
		for (Location location : locations) {
			locationsStrings.add(location.getLocation());
		}

		List<MoveEntity> moves = BookMovesDao.findByYear(year, locationsStrings.toArray());

		return mapToMove(moves);
	}


	private List<Move> mapToMove(List<MoveEntity> moveEntityList) {
		List<Move> movesList = new ArrayList<Move>();
		
		for(int i = 0; i < moveEntityList.size(); i++) {
			Move move = new Move();
			move.setId(moveEntityList.get(i).getID());
			List<Note> notes = NoteEntityToNote(moveEntityList.get(i).getNotes());
			move.setNotes(notes);
			move.setComment(moveEntityList.get(i).getComment());
			move.setStartsAt(moveEntityList.get(i).getMoveStart());
			move.setFromCity(moveEntityList.get(i).getfromCity());
			move.setFromState(moveEntityList.get(i).getFromState());
			move.setFromStreet(moveEntityList.get(i).getfromStreet());
			move.setFromZip(moveEntityList.get(i).getFromZip());
			move.setToCity(moveEntityList.get(i).getToCity());
			move.setToState(moveEntityList.get(i).getToState());
			move.setToStreet(moveEntityList.get(i).getToStreet());
			move.setToZip(moveEntityList.get(i).getToZip());
			move.setDateOfBooking(moveEntityList.get(i).getDateOfBooking());
			move.setStatus(moveEntityList.get(i).getStatus());
			move.setEndsAt(moveEntityList.get(i).getMoveEnd());
			move.setTitle(moveEntityList.get(i).getMoveTitle());
			UserView userView = new UserView();
			userView.setPhone(moveEntityList.get(i).getUser().getPhone());
			userView.setLastName(moveEntityList.get(i).getUser().getLastName());
			userView.setId(moveEntityList.get(i).getUser().getId());
			userView.setFirstName(moveEntityList.get(i).getUser().getFirstName());
			userView.setEmail(moveEntityList.get(i).getUser().getEmail());
			move.setUser(userView);
			movesList.add(move);
		}
		
		return movesList;
	}
	
	private List<Note> NoteEntityToNote(List<NoteEntity> notesEntityList) {
		
		List<Note> noteList = new ArrayList<Note>();
		
		for(int i = 0; i < notesEntityList.size(); i++) {
			Note note = new Note();
			note.setAuthor(notesEntityList.get(i).getAuthor());
			note.setComment(notesEntityList.get(i).getComment());
			note.setDate(notesEntityList.get(i).getDate());
			note.setID(notesEntityList.get(i).getID());
			noteList.add(note);
		}
		
		return noteList;
	}
}

