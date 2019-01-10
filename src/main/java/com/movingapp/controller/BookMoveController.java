package com.movingapp.controller;

import com.movingapp.dao.AuthorityRepo;
import com.movingapp.dao.ConfirmationTokenRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.Authority;
import com.movingapp.entity.ConfirmationToken;
import com.movingapp.entity.User;
import com.movingapp.helper.EmailTemplateHelper;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.EmailService;
import com.movingapp.service.MoveMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.Move;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class BookMoveController {
	
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

	@Autowired
	private EmailService emailService;

	@Autowired
	private ConfirmationTokenRepo confirmationTokenRepo;

	@Autowired
	private EmailTemplateHelper emailTemplateHelper;

	@RequestMapping(value = "/bookMove",method = RequestMethod.POST ,consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Move> submitMove(@RequestBody Move move, @Param("userId") String userId) {
		 //Gson gson = new Gson();
		 //Move insertMove = gson.fromJson(move, Move.class);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date input = new Date();
		LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		move.setDateOfBooking(date);
		User userExists;
		if(userId != null) {
			userExists = userService.findById(Long.parseLong(userId));
			UserView userView = new UserView();
			userView.setId(userExists.getId());
			userView.setFirstName(userExists.getFirstName());
			userView.setLastName(userExists.getLastName());
			move.setUser(userView);
			move.setStatus("unconfirmed move");

			String html = emailTemplateHelper.bookingMoveConfirmationEmailTemplate(userView.getFirstName() + " " + userView.getLastName());

			emailService.sendMail("noreply@domain.com", userExists.getEmail(), "Move Booked", html);
		} else {
			userExists = userService.findByEmail(move.getUser().getEmail());
			if(userExists != null) {
				return new ResponseEntity<>(move,HttpStatus.BAD_REQUEST);
			}
			User user = createNewUser(move.getUser());
			UserView userView = new UserView();
			userView.setId(user.getId());
			userView.setEmail(user.getEmail());
			userView.setFirstName(user.getFirstName());
			userView.setLastName(user.getLastName());
			move.setUser(userView);
			move.setStatus("unconfirmed user");

			String appUrl = request.getScheme() + "://" + request.getServerName();

			String html = emailTemplateHelper.confirmationEmailTemplate(user.getFirstName() + " " + user.getLastName(),  appUrl + "/#/confirm?token=" + user.getConfirmationToken().getToken(), user.getEmail());

			emailService.sendMail("noreply@domain.com", user.getEmail(), "Move Booked", html);
		}

		MoveEntity moveEntity = moveMapping.moveToMoveEntity(move);
		
//		List<ChargeEntity> charges = new ArrayList<ChargeEntity>();
//		ChargeEntity charge = new ChargeEntity();
//		charge.setAmount(1);
//		charge.setDate(new Date());
//		charge.setMove(moveEntity);
//		charges.add(charge);
//		moveEntity.setCharges(charges);
		moveEntity.setMoveTitle(move.getUser().getFirstName() + " " + move.getUser().getLastName());
		moveEntity.setDateOfBooking(LocalDate.now());
		moveEntity.setMoveStart(moveEntity.getMoveStart().plusHours(8));
		moveEntity.setMoveEnd(moveEntity.getMoveStart().plusHours(1));
		moveEntity.setElevator(false);
		moveEntity.setArtwork(false);
		moveEntity.setGroundFloor(false);
		moveEntity.setAntiques(false);
		BookMovesDao.save(moveEntity);
		//BookMovesDao.insert(insertMove.getFirstName(), insertMove.getLastName(), insertMove.getEmail(), insertMove.getPhone(), insertMove.getfromStreet(), insertMove.getfromCity(), insertMove.getFromZip(), insertMove.getFromState(), insertMove.getToStreet(), insertMove.getToCity(), insertMove.getToZip(), insertMove.getToState(), insertMove.getComments(), insertMove.getDate());

		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkUser(@Param("email") String email) {
		User userExists;
		userExists = userService.findByEmail(email);
		if(userExists != null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	private User createNewUser(UserView user) {
		User saveUser = new User();
		saveUser.setPhone(user.getPhone());
		saveUser.setEmail(user.getEmail().toLowerCase());
		saveUser.setLastName(user.getLastName());
		saveUser.setFirstName(user.getFirstName());
		Optional<Authority> authority = authorityRepo.findById((long)3);
		Set<Authority> authorityList = new HashSet<>();
		authorityList.add(authority.get());
		saveUser.setAuthorities(authorityList);
		saveUser.setEnabled(false);
		ConfirmationToken confirmationToken = new ConfirmationToken(UUID.randomUUID().toString());
		saveUser.setConfirmationToken(confirmationToken);
		confirmationToken.setUser(saveUser);

		confirmationTokenRepo.save(confirmationToken);

		return userRepo.save(saveUser);
	}
}

