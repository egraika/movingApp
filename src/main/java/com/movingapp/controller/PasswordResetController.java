package com.movingapp.controller;

import com.movingapp.dao.PasswordResetTokenRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.PasswordResetToken;
import com.movingapp.entity.User;
import com.movingapp.service.EmailService;
import com.movingapp.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Controller
@Transactional
public class PasswordResetController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordResetTokenRepo PasswordResetTokenRepo;

    // Process form input data
    @RequestMapping(value = "/sendResetToken",method = RequestMethod.POST ,consumes = "application/json")
    @Transactional
    public @ResponseBody ResponseEntity processRegistrationForm(@RequestBody String email, HttpServletRequest request) {

        // Lookup user in database by e-mail
        User user = userService.findByEmail(email);
        PasswordResetToken passwordResetToken = user.getPasswordResetToken();

        System.out.println(user);

        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        user.setPasswordResetToken(null);
        user = userRepo.save(user);

        // Generate random 36-character string token for confirmation link
        PasswordResetToken token = new PasswordResetToken(UUID.randomUUID().toString());

        PasswordResetTokenRepo.save(token);
        user.setPasswordResetToken(token);
        user = userRepo.save(user);

        String appUrl = request.getScheme() + "://" + request.getServerName();

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(user.getEmail());
        registrationEmail.setSubject("Password Reset");
        registrationEmail.setText("To reset your password, please click the link below:\n"
                + appUrl + "/#/reset/password/confirm?token=" + user.getPasswordResetToken().getToken() + "\n\n If you did not request a password reset please ignore this email.");
        registrationEmail.setFrom("noreply@domain.com");

        emailService.sendEmail(registrationEmail);

        return new ResponseEntity(HttpStatus.OK);
    }

    // Process confirmation link
    @RequestMapping(value="/confirmPasswordResetToken", method = RequestMethod.GET)
    public ResponseEntity<User> confirmPasswordResetToken(@RequestParam("token") String token) {

        PasswordResetToken passwordResetToken = PasswordResetTokenRepo.findByToken(token);

        if(passwordResetToken == null || passwordResetToken.getExpiryDate().after(new Date())) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = passwordResetToken.getUser();

        if (user == null) { // No token found in DB
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // dont return password
        user.setPassword(null);

        return new ResponseEntity(user, HttpStatus.OK);
    }
}