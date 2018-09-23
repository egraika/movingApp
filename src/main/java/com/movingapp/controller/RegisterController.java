package com.movingapp.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.movingapp.dao.UserRepo;
import com.movingapp.entity.User;
import com.movingapp.service.EmailService;
import com.movingapp.service.UserService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

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
        user.setConfirmationToken(UUID.randomUUID().toString());

        user = userRepo.save(user);

        String appUrl = request.getScheme() + "://" + request.getServerName();

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + appUrl + "/#/confirm?token=" + user.getConfirmationToken());
        registrationEmail.setFrom("noreply@domain.com");

        emailService.sendEmail(registrationEmail);

        return new ResponseEntity(HttpStatus.OK);
    }

    // Process confirmation link
    @RequestMapping(value="/confirmToken", method = RequestMethod.GET)
    public ResponseEntity<String> showConfirmationPage(@RequestParam("token") String token) {

        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    // Process confirmation link
    @RequestMapping(value="/confirm", method = RequestMethod.POST)
    public ResponseEntity<String> processConfirmationForm(@RequestBody User user) {

        Zxcvbn passwordCheck = new Zxcvbn();

        Strength strength = passwordCheck.measure(user.getPassword());

        if (strength.getScore() < 3) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // Find the user associated with the reset token
        User foundUser = userService.findByConfirmationToken(user.getConfirmationToken());

        // Set new password
        foundUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Set user to enabled
        foundUser.setEnabled(true);

        // Save user
        userService.saveUser(foundUser);

        return new ResponseEntity(HttpStatus.OK);
    }

}