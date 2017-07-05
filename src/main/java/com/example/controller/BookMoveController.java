package com.example.controller;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ChargeEntity;
import com.example.model.MoveEntity;
import com.example.model.NoteEntity;
import com.example.view.ChargeView;
import com.example.view.Move;
import com.example.view.Note;
import com.example.view.StripeView;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class BookMoveController {
	
	@Autowired
	private com.example.dao.BookMovesDao BookMovesDao;
	
	@Autowired
	private HttpServletRequest request;
	
	@Transactional
	@RequestMapping(value = "/updateMove",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Move updateMove(@RequestBody Move move) {
		
		MoveEntity moveEntity = moveToMoveEntity(move);
		BookMovesDao.save(moveEntity);

		return MoveEntityToMove(moveEntity);
	}

	@RequestMapping(value = "/stripeDeposit",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Move stripeDesposit(@RequestBody StripeView stripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		// Set your secret key: remember to change this to your live secret key in production
		// See your keys here: https://dashboard.stripe.com/account/apikeys
		Stripe.apiKey = "sk_test_356VkXvxAv3KPrTnpY6iJkTb";
		//Stripe.apiKey = "sk_live_xi03Kbf02Hqd57v7Zc6lFyge";
		
		// Token is created using Stripe.js or Checkout!
		// Get the payment token submitted by the form:
		String token = stripe.getToken();

		// Create a Customer:
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("email", "ericgraika@gmail.com");
		customerParams.put("source", token);
		Customer customer = Customer.create(customerParams);

		// Charge the Customer instead of the card:
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", 100);
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", customer.getId());
		Charge charge = Charge.create(chargeParams);

		// YOUR CODE: Save the customer ID and other info in a database for later.

		// YOUR CODE (LATER): When it's time to charge the customer again, retrieve the customer ID.
//		Map<String, Object> chargeParams = new HashMap<String, Object>();
//		chargeParams.put("amount", 1500); // $15.00 this time
//		chargeParams.put("currency", "usd");
//		chargeParams.put("customer", customerId);
//		Charge charge = Charge.create(chargeParams);
		Move move = new Move();
		move.setStripeCustomerID(customer.getId());
		return move;
	}
	
	@RequestMapping(value = "/bookMove",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public Move submitMove(@RequestBody Move move) {
		 //Gson gson = new Gson();
		 //Move insertMove = gson.fromJson(move, Move.class);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		move.setDateOfBooking(date);
		MoveEntity moveEntity = moveToMoveEntity(move);
		
		List<ChargeEntity> charges = new ArrayList<ChargeEntity>();
		ChargeEntity charge = new ChargeEntity();
		charge.setAmount(1);
		charge.setDate(date);
		charge.setMove(moveEntity);
		charges.add(charge);
		moveEntity.setCharges(charges);
		moveEntity.setStatus("open");
		
		BookMovesDao.save(moveEntity);
		//BookMovesDao.insert(insertMove.getFirstName(), insertMove.getLastName(), insertMove.getEmail(), insertMove.getPhone(), insertMove.getfromStreet(), insertMove.getfromCity(), insertMove.getFromZip(), insertMove.getFromState(), insertMove.getToStreet(), insertMove.getToCity(), insertMove.getToZip(), insertMove.getToState(), insertMove.getComments(), insertMove.getDate());
		
		final String username = "ericgraika@gmail.com";
		final String password = "John1956@";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("MoveMuscle@gmail.com"));
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(move.getEmail()));
					message.setSubject("Move Muscle Comfirmation");
					message.setText("Your move for the date of " + move.getDateOfBooking().toString() + " has been booked");

					Transport.send(message);

					System.out.println("Done");

				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
		
		return MoveEntityToMove(moveEntity);
	}
	
//	@RequestMapping(value = "/getBookedMoves",method = RequestMethod.GET)
//	@ResponseBody
//	public List<Move> getBookedMoves() {
//		
//		List<MoveEntity> moveEntityList = BookMovesDao.findAll();
//		List<Move> moves = MoveEntityToMoves(moveEntityList);
//		
//		return moves;
//	}
	
	@RequestMapping(value = "/getMove",method = RequestMethod.POST)
	@ResponseBody
	public Move getMove(@RequestParam("id") int moveID) {
		
		MoveEntity moveEntity = BookMovesDao.findById(moveID);
		Move move = MoveEntityToMove(moveEntity);
		
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
		
		List<Note> newNotesList = NoteEntityToNote(newNoteEntity);
		return newNotesList.get(newNotesList.size()-1);
	}
	
	@RequestMapping(value = "/addCharge",method = RequestMethod.POST)
	@ResponseBody
	public ChargeView addCharge(@RequestParam("customerID") String customerID, @RequestParam("amount") double amount, @RequestParam("id") int moveID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		Stripe.apiKey = "sk_test_356VkXvxAv3KPrTnpY6iJkTb";
		//Stripe.apiKey = "sk_live_xi03Kbf02Hqd57v7Zc6lFyge";
		MoveEntity moveEntity = BookMovesDao.findById(moveID);
		List<ChargeEntity> chargeEntityList = moveEntity.getCharges();
		ChargeEntity charge = new ChargeEntity();
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		charge.setAmount(amount);
		charge.setDate(date);
		charge.setMove(moveEntity);
		chargeEntityList.add(charge);
		moveEntity.setCharges(chargeEntityList);;

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", (int) amount*100); // $15.00 this time
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", customerID);
		Charge chargeCustomer = Charge.create(chargeParams);
		
		MoveEntity moveAfterSave = BookMovesDao.save(moveEntity);
		List<ChargeEntity> newChargeEntity = moveAfterSave.getCharges();
		
		List<ChargeView> newChargeList = ChargeEntityToCharge(newChargeEntity);
		return newChargeList.get(newChargeList.size()-1);
	}
	
	private Move MoveEntityToMove(MoveEntity moveEntity) {
		Move move = new Move();
		move.setID(moveEntity.getID());
		List<Note> notes = NoteEntityToNote(moveEntity.getNotes());
		move.setNotes(notes);
		move.setComment(moveEntity.getComment());
		move.setDateOfMove(moveEntity.getDateOfMove());
		move.setEmail(moveEntity.getEmail());
		move.setFirstName(moveEntity.getFirstName());
		move.setfromCity(moveEntity.getfromCity());
		move.setFromState(moveEntity.getFromState());
		move.setfromStreet(moveEntity.getfromStreet());
		move.setFromZip(moveEntity.getFromZip());
		move.setLastName(moveEntity.getLastName());
		move.setPhone(moveEntity.getPhone());
		move.setToCity(moveEntity.getToCity());
		move.setToState(moveEntity.getToState());
		move.setToStreet(moveEntity.getToStreet());
		move.setToZip(moveEntity.getToZip());
		move.setDateOfBooking(moveEntity.getDateOfBooking());
		move.setStripeCustomerID(moveEntity.getStripeCustomerID());
		move.setCharges(ChargeEntityToCharge(moveEntity.getCharges()));
		move.setStatus(moveEntity.getStatus());
		
		return move;
	}

	private List<Move> MoveEntityToMoves(List<MoveEntity> moveEntityList) {
		
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
	
private List<NoteEntity> NoteToNoteEntity(List<Note> notesList, MoveEntity move) {
		
		List<NoteEntity> noteEntityList = new ArrayList<NoteEntity>();
		
		for(int i = 0; i < notesList.size(); i++) {
			NoteEntity note = new NoteEntity();
			note.setAuthor(notesList.get(i).getAuthor());
			note.setComment(notesList.get(i).getComment());
			note.setDate(notesList.get(i).getDate());
			note.setID(notesList.get(i).getID());
			note.setMove(move);
			noteEntityList.add(note);
		}
		
		return noteEntityList;
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

private List<ChargeEntity> ChargeToChargeEntity(List<ChargeView> chargeList, MoveEntity Move) {
	
	List<ChargeEntity> chargeEntityList = new ArrayList<ChargeEntity>();
	
	for(int i = 0; i < chargeList.size(); i++) {
		ChargeEntity charge = new ChargeEntity();
		charge.setAmount(chargeList.get(i).getAmount());
		charge.setDate(chargeList.get(i).getDate());
		charge.setID(chargeList.get(i).getID());
		charge.setMove(Move);
		chargeEntityList.add(charge);
	}
	
	return chargeEntityList;
}

	private MoveEntity moveToMoveEntity(Move move) {
		
		MoveEntity moveEntity = new MoveEntity();
		moveEntity.setID(move.getID());
		moveEntity.setDateOfMove(move.getDateOfMove());
		moveEntity.setComment(move.getComment());
		moveEntity.setEmail(move.getEmail());
		moveEntity.setFirstName(move.getFirstName());
		moveEntity.setfromCity(move.getfromCity());
		moveEntity.setFromState(move.getFromState());
		moveEntity.setfromStreet(move.getfromStreet());
		moveEntity.setFromZip(move.getFromZip());
		moveEntity.setLastName(move.getLastName());
		moveEntity.setPhone(move.getPhone());
		moveEntity.setToCity(move.getToCity());
		moveEntity.setToState(move.getToState());
		moveEntity.setToStreet(move.getToStreet());
		moveEntity.setToZip(move.getToZip());
		moveEntity.setDateOfBooking(move.getDateOfBooking());
		moveEntity.setStatus(move.getStatus());
		moveEntity.setStripeCustomerID(move.getStripeCustomerID());
		moveEntity.setCharges(ChargeToChargeEntity(move.getCharges(), moveEntity));
		moveEntity.setNotes(NoteToNoteEntity(move.getNotes(), moveEntity));
		
		return moveEntity;
	}
}

