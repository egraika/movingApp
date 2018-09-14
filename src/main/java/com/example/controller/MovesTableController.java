package com.example.controller;

import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Location;
import com.example.entity.User;
import com.example.model.ChargeEntity;
import com.example.model.ContactEntity;
import com.example.model.MoveEntity;
import com.example.model.NoteEntity;
import com.example.model.TableState;
import com.example.view.ChargeView;
import com.example.view.Contact;
import com.example.view.Employee;
import com.example.view.Move;
import com.example.view.Note;
import com.google.gson.Gson;

@Controller
public class MovesTableController {
	
	@Autowired
	private com.example.dao.BookMovesDao BookMovesDao;

	@Autowired
	private com.example.dao.UserRepo UserRepo;
	
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

		Integer searchLong = new Integer(0);
		try {
			searchLong = Integer.parseInt(search);
		} catch (Exception e) {

		}

		List<Move> moves;
		Page<Move> pages = null;
		Page<MoveEntity> moveEntities;

		User user = UserRepo.findById(userid);
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


	private List<Move> mapToMove(List<MoveEntity> moveEntityList) {
		List<Move> movesList = new ArrayList<Move>();
		
		for(int i = 0; i < moveEntityList.size(); i++) {
			Move move = new Move();
			move.setID(moveEntityList.get(i).getID());
			List<Note> notes = NoteEntityToNote(moveEntityList.get(i).getNotes());
			move.setNotes(notes);
			move.setComment(moveEntityList.get(i).getComment());
			move.setDateOfMove(moveEntityList.get(i).getDateOfMove());
			move.setEmail(moveEntityList.get(i).getEmail());
			move.setFirstName(moveEntityList.get(i).getFirstName());
			move.setfromCity(moveEntityList.get(i).getfromCity());
			move.setFromState(moveEntityList.get(i).getfromStreet());
			move.setfromStreet(moveEntityList.get(i).getfromStreet());
			move.setFromZip(moveEntityList.get(i).getFromZip());
			move.setLastName(moveEntityList.get(i).getLastName());
			move.setPhone(moveEntityList.get(i).getPhone());
			move.setToCity(moveEntityList.get(i).getToCity());
			move.setToState(moveEntityList.get(i).getToState());
			move.setToStreet(moveEntityList.get(i).getToStreet());
			move.setToZip(moveEntityList.get(i).getToZip());
			move.setDateOfBooking(moveEntityList.get(i).getDateOfBooking());
			move.setStripeCustomerID(moveEntityList.get(i).getStripeCustomerID());
			move.setCharges(ChargeEntityToCharge(moveEntityList.get(i).getCharges()));
			move.setStatus(moveEntityList.get(i).getStatus());
			
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
	
	private List<ChargeView> ChargeEntityToCharge(List<ChargeEntity> chargeEntityList) {
		
		List<ChargeView> chargeList = new ArrayList<ChargeView>();
		
		for(int i = 0; i < chargeEntityList.size(); i++) {
			ChargeView charge = new ChargeView();
			charge.setAmount(chargeEntityList.get(i).getAmount());
			charge.setDate(chargeEntityList.get(i).getDate());
			charge.setID(chargeEntityList.get(i).getID());
			chargeList.add(charge);
		}
		
		return chargeList;
	}
	
}

