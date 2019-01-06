package com.movingapp.controller;

import com.movingapp.dao.AuthorityRepo;
import com.movingapp.dao.BookMovesDao;
import com.movingapp.dao.ConfirmationTokenRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.Authority;
import com.movingapp.entity.ConfirmationToken;
import com.movingapp.entity.User;
import com.movingapp.helper.EmailTemplateHelper;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.EmailService;
import com.movingapp.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Controller
@Transactional
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    private AuthorityRepo authorityRepo;

    @Autowired
    private BookMovesDao bookMovesDao;

    @Autowired
    private EmailTemplateHelper emailTemplateHelper;

    // Process form input data
    @RequestMapping(value = "/register",method = RequestMethod.POST ,consumes = "application/json")
    public @ResponseBody ResponseEntity processRegistrationForm(@RequestBody User user, HttpServletRequest request) {

        // Lookup user in database by e-mail
        User userExists = userService.findByEmail(user.getEmail());

        System.out.println(userExists);

        if (userExists != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        // Disable user until they click on confirmation link in email
        user.setEnabled(false);

        // Generate random 36-character string token for confirmation link
        ConfirmationToken confirmationToken = new ConfirmationToken(UUID.randomUUID().toString());
        user.setConfirmationToken(confirmationToken);
        confirmationToken.setUser(user);
        user.setConfirmationToken(confirmationToken);

        Optional<Authority> authority = authorityRepo.findById((long)3);
        Set<Authority> authorityList = new HashSet<>();
        authorityList.add(authority.get());
        user.setAuthorities(authorityList);

        user = userRepo.save(user);
        confirmationTokenRepo.save(confirmationToken);

        String appUrl = request.getScheme() + "://" + request.getServerName();

        String html = emailTemplateHelper.confirmationEmailTemplate(user.getFirstName() + " " + user.getLastName(),  appUrl + "/#/confirm?token=" + user.getConfirmationToken().getToken(), user.getEmail());

        emailService.sendMail("noreply@domain.com", user.getEmail(), "Move Booked", html);

        return new ResponseEntity(HttpStatus.OK);
    }

    // Process confirmation link
    @RequestMapping(value="/confirmToken", method = RequestMethod.GET)
    public ResponseEntity<User> showConfirmationPage(@RequestParam("token") String token) {

        ConfirmationToken confirmationToken = confirmationTokenRepo.findByToken(token);

        if(confirmationToken == null || confirmationToken.getExpiryDate().before(new Date())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = confirmationToken.getUser();

        if (user == null) { // No token found in DB
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        user.setConfirmationToken(null);

        return new ResponseEntity(user, HttpStatus.OK);
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processConfirmationForm(@RequestBody User user) {

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure(user.getPassword());

        if (strength.getScore() < 1) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // Find the user associated with the reset token
        User foundUser = userRepo.findByEmail(user.getEmail());

        // Set new password
        foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Set user to enabled
        foundUser.setEnabled(true);

        // Save user
        userService.saveUser(foundUser);

        for (MoveEntity move : foundUser.getMoves()) {
            move.setStatus("unconfirmed move");
            bookMovesDao.save(move);
        }

        confirmationTokenRepo.deleteByUserId(user.getId());

        return new ResponseEntity(HttpStatus.OK);
    }

}