package com.movingapp.controller;

import com.movingapp.dao.AuthorityRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.model.MoveEntity;
import com.movingapp.model.NoteEntity;
import com.movingapp.service.MoveMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.Move;
import com.movingapp.view.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Controller
public class editMoveController {
	
	@Autowired
	private com.movingapp.dao.BookMovesDao BookMovesDao;
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityRepo authorityRepo;

	@Autowired
	private MoveMapping moveMapping;

	@Transactional
	@RequestMapping(value = "/updateMove",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Move updateMove(@RequestBody Move move) {
		
		MoveEntity moveEntity = moveMapping.moveToMoveEntity(move);
		if(moveEntity.getMoveStart().isAfter(moveEntity.getMoveEnd())) {
			moveEntity.setMoveEnd(moveEntity.getMoveStart().plusHours(1));
		}
		moveEntity.setMoveTitle(move.getUser().getFirstName() + " " + move.getUser().getLastName());
		BookMovesDao.save(moveEntity);

		return moveMapping.MoveEntityToMove(moveEntity);
	}
	
	@RequestMapping(value = "/getMove",method = RequestMethod.POST)
	@ResponseBody
	public Move getMove(@RequestParam("id") int moveID) {
		
		MoveEntity moveEntity = BookMovesDao.findById(moveID);
		Move move = moveMapping.MoveEntityToMove(moveEntity);
		
		return move;
	}
	
	@RequestMapping(value = "/addNote",method = RequestMethod.POST)
	@ResponseBody
	public Note addNote(@RequestParam("id") int moveID, @RequestParam("username") String username) {
		
		MoveEntity moveEntity = BookMovesDao.findById(moveID);
		List<NoteEntity> noteEntityList = moveEntity.getNotes();
		NoteEntity note = new NoteEntity();
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		note.setDate(date);
		note.setAuthor(username);
		note.setMove(moveEntity);
		noteEntityList.add(note);
		moveEntity.setNotes(noteEntityList);
		
		MoveEntity moveAfterSave = BookMovesDao.save(moveEntity);
		List<NoteEntity> newNoteEntity = moveAfterSave.getNotes();
		
		List<Note> newNotesList = moveMapping.NoteEntityToNote(newNoteEntity);
		return newNotesList.get(newNotesList.size()-1);
	}
}
