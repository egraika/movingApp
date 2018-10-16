package com.movingapp.controller;

import com.google.gson.Gson;
import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import com.movingapp.model.ChargeEntity;
import com.movingapp.model.MoveEntity;
import com.movingapp.model.NoteEntity;
import com.movingapp.model.TableState;
import com.movingapp.view.ChargeView;
import com.movingapp.view.Move;
import com.movingapp.view.Note;
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
public class MyMovesController {
	
	@Autowired
	private com.movingapp.dao.BookMovesDao BookMovesDao;

	@Autowired
	private com.movingapp.dao.UserRepo UserRepo;
	
	@RequestMapping(value = "/getMyMoves", method = RequestMethod.GET)
	@ResponseBody
	public Page<Move> getMyMoves(@RequestParam("tableState") String state, @RequestParam("userid") Long userid) {
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

		Optional<User> optionalUser = UserRepo.findById(userid);
		User user =  optionalUser.get();

		if (search == "" && statusSearch == "") {
			moveEntities = BookMovesDao.findAllMyMoves(pageable, user);
		} else {
			moveEntities = BookMovesDao.findAllMyMoves(search, statusSearch, pageable, user);
		}
		moves = mapToMove(moveEntities.getContent());

		pages = new PageImpl<Move>(moves, pageable, moveEntities.getTotalElements());

		return pages;
	}


	private List<Move> mapToMove(List<MoveEntity> moveEntityList) {
		List<Move> movesList = new ArrayList<Move>();
		
		for(int i = 0; i < moveEntityList.size(); i++) {
			Move move = new Move();
			move.setId(moveEntityList.get(i).getID());
			List<Note> notes = NoteEntityToNote(moveEntityList.get(i).getNotes());
			move.setNotes(notes);
			MoveEntity moveEntity = moveEntityList.get(i);
			move.setComment(moveEntity.getComment());
			move.setStartsAt(moveEntity.getMoveStart());
			move.setFromCity(moveEntity.getfromCity());
			move.setFromState(moveEntity.getFromState());
			move.setFromStreet(moveEntity.getfromStreet());
			move.setFromZip(moveEntity.getFromZip());
			move.setToCity(moveEntity.getToCity());
			move.setToState(moveEntity.getToState());
			move.setToStreet(moveEntity.getToStreet());
			move.setToZip(moveEntity.getToZip());
			move.setDateOfBooking(moveEntity.getDateOfBooking());
			move.setStatus(moveEntity.getStatus());
			move.setEndsAt(moveEntity.getMoveEnd());
			move.setTitle(moveEntity.getMoveTitle());
			move.setArtwork(moveEntity.getArtwork());
			move.setElevator(moveEntity.getElevator());
			move.setGroundFloor(moveEntity.getGroundFloor());
			move.setAntiques(moveEntity.getAntiques());
			move.setNumberOfBoxes(moveEntity.getNumberOfBoxes());
			move.setNumberOfLargeItems(moveEntity.getNumberOfLargeItems());
			
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

