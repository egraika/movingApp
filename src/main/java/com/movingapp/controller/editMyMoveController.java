package com.movingapp.controller;

import com.movingapp.entity.User;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.MoveMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@Controller
public class editMyMoveController {
	
	@Autowired
	private com.movingapp.dao.BookMovesDao BookMovesDao;

	@Autowired
	private MoveMapping moveMapping;

	@Autowired
	private UserService userService;

	@Transactional
	@RequestMapping(value = "/updateMyMove",method = RequestMethod.POST ,consumes = "application/json")
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

	@RequestMapping(value = "/getMyMove",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Move> getMove(@RequestParam("id") int moveID, @RequestParam("userid") long userid) {

		User user = userService.findById(userid);
		MoveEntity moveEntity = BookMovesDao.findById(moveID);

		if(moveEntity.getUser().getId() != user.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Move move = moveMapping.MoveEntityToMove(moveEntity);
		
		return new ResponseEntity<>(move, HttpStatus.OK);
	}
}

