package com.movingapp.controller;

import com.movingapp.Constants.MovingAppConstants;
import com.movingapp.dao.ChargesDao;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.User;
import com.movingapp.helper.EmailTemplateHelper;
import com.movingapp.model.ChargeEntity;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.*;
import com.movingapp.view.ChargeView;
import com.movingapp.view.StripeView;
import com.movingapp.view.UserView;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class StripeController {

    @Autowired
    private com.movingapp.dao.BookMovesDao BookMovesDao;

    @Autowired
    private MoveMapping moveMapping;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapping userMapping;

    @Autowired
    private ChargesDao chargesDao;

    @Autowired
    EmailTemplateHelper emailTemplateHelper;

    @Autowired
    EmailService emailService;

    @RequestMapping(value = "/setCreditCard",method = RequestMethod.POST ,consumes = "application/json")
    @ResponseBody
    public ResponseEntity<UserView> setCreditCard(@RequestBody StripeView stripe, @RequestParam("userid") long userid) throws StripeException, UnsupportedEncodingException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = MovingAppConstants.apiKey;

        User user = userService.findById(userid);

        // Token is created using Stripe.js or Checkout!
        // Get the payment token submitted by the form:
        String token = stripe.getToken();
        Customer customer;
        Card card;
        if(user.getStripeCustomerID() == null) {
            // Create a Customer:
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("email", "ericgraika@gmail.com");
            customerParams.put("source", token);
            customer = Customer.create(customerParams);
            card = (Card) customer.getSources().retrieve(customer.getDefaultSource());
        } else {
            customer = Customer.retrieve(user.getStripeCustomerID());
            card = (Card) customer.getSources().retrieve(customer.getDefaultSource());
            Map<String, Object> params = new HashMap<>();
            params.put("source", token);
            customer.update(params);
        }
        // Charge the Customer instead of the card:
//        Map<String, Object> chargeParams = new HashMap<String, Object>();
//        chargeParams.put("amount", 0);
//        chargeParams.put("currency", "usd");
//        chargeParams.put("customer", customer.getId());
//        Charge charge = Charge.create(chargeParams);

        // YOUR CODE: Save the customer ID and other info in a database for later.

        // YOUR CODE (LATER): When it's time to charge the customer again, retrieve the customer ID.
//		Map<String, Object> chargeParams = new HashMap<String, Object>();
//		chargeParams.put("amount", 1500); // $15.00 this time
//		chargeParams.put("currency", "usd");
//		chargeParams.put("customer", customerId);
//		Charge charge = Charge.create(chargeParams);
        user.setCcExpirationDate(card.getExpMonth() + "/" + card.getExpYear());
        user.setCcLastFour(card.getLast4());
        user.setCcCardType(card.getBrand());
        user.setStripeCustomerID(customer.getId());
        userService.saveUser(user);
        UserView userView = userMapping.UserToUserView(user);
        return new ResponseEntity<>(userView, HttpStatus.OK);
    }


    @RequestMapping(value = "/addCharge",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ChargeView> addCharge(@RequestParam("amount") double amount, @RequestParam("id") int moveID) throws StripeException {

        Stripe.apiKey = MovingAppConstants.apiKey;
        MoveEntity moveEntity = BookMovesDao.findById(moveID);
        User user = userService.findById(moveEntity.getUser().getId());
        List<ChargeEntity> chargeEntityList = user.getCharges();
        ChargeEntity charge = new ChargeEntity();

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        charge.setType("Charge");
        charge.setAmount(amount);
        charge.setDate(date);
        charge.setUser(user);
        charge.setMoveid(moveEntity.getID());
        chargeEntityList.add(charge);
        user.setCharges(chargeEntityList);

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int) amount*100); // $15.00 this time
        chargeParams.put("currency", "usd");
        chargeParams.put("customer", user.getStripeCustomerID());
        chargeParams.put("receipt_email", user.getEmail());
        Charge chargeCustomer = Charge.create(chargeParams);
        if(!chargeCustomer.getStatus().equals("succeeded")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        charge.setChargeid(chargeCustomer.getId());

        User userAfterSave = userRepo.save(user);

        String html = emailTemplateHelper.chargeEmailTemplate(amount, user.getCcLastFour());

        emailService.sendMail("noreply@domain.com", user.getEmail(), "Move Booked", html);

        List<ChargeEntity> newChargeEntity = userAfterSave.getCharges();

        List<ChargeView> newChargeList = ChargeMapping.ChargeEntityToCharge(newChargeEntity);
        return new ResponseEntity(newChargeList.get(newChargeList.size()-1), HttpStatus.OK);
    }

    @RequestMapping(value = "/refundCharge",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ChargeView> refundCharge(@RequestParam("amount") int amount, @RequestParam("id") int chargeId) throws StripeException {

        Stripe.apiKey = MovingAppConstants.apiKey;
        ChargeEntity chargeEntity;
        Optional<ChargeEntity> optionalChargeEntity = chargesDao.findById(chargeId);
        if(optionalChargeEntity.isPresent()) {
            chargeEntity = optionalChargeEntity.get();
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        User user = chargeEntity.getUser();
        List<ChargeEntity> chargeEntities = user.getCharges();

        Map<String, Object> params = new HashMap<>();
        params.put("charge",chargeEntity.getChargeid());
        params.put("amount", amount);
        Refund refund = Refund.create(params);

        if(!refund.getStatus().equals("succeeded")) {
            ChargeView chargeView =  new ChargeView();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ChargeEntity newChargeEntity = new ChargeEntity();
        Date date = new Date();
        newChargeEntity.setType("Refund");
        newChargeEntity.setAmount(amount);
        newChargeEntity.setDate(date);
        newChargeEntity.setUser(user);
        newChargeEntity.setMoveid(chargeEntity.getMoveid());
        chargeEntities.add(newChargeEntity);
        user.setCharges(chargeEntities);

        User userAfterSave = userRepo.save(user);
        List<ChargeEntity> newChargeEntities = userAfterSave.getCharges();

        List<ChargeView> newChargeList = ChargeMapping.ChargeEntityToCharge(newChargeEntities);
        return new ResponseEntity(newChargeList.get(newChargeList.size()-1), HttpStatus.OK);
    }
}
