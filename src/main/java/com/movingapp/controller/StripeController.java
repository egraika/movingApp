package com.movingapp.controller;

import com.movingapp.Constants.MovingAppConstants;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.User;
import com.movingapp.model.ChargeEntity;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.ChargeMapping;
import com.movingapp.service.MoveMapping;
import com.movingapp.service.UserMapping;
import com.movingapp.service.UserService;
import com.movingapp.view.ChargeView;
import com.movingapp.view.StripeView;
import com.movingapp.view.UserView;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/setCreditCard",method = RequestMethod.POST ,consumes = "application/json")
    @ResponseBody
    public ResponseEntity<UserView> setCreditCard(@RequestBody StripeView stripe, @RequestParam("userid") long userid) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
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
    public ChargeView addCharge(@RequestParam("amount") double amount, @RequestParam("id") int moveID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {

        Stripe.apiKey = MovingAppConstants.apiKey;
        MoveEntity moveEntity = BookMovesDao.findById(moveID);
        User user = userService.findById(moveEntity.getUser().getId());
        List<ChargeEntity> chargeEntityList = user.getCharges();
        ChargeEntity charge = new ChargeEntity();

        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
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
        Charge chargeCustomer = Charge.create(chargeParams);

        User userAfterSave = userRepo.save(user);
        List<ChargeEntity> newChargeEntity = userAfterSave.getCharges();

        List<ChargeView> newChargeList = ChargeMapping.ChargeEntityToCharge(newChargeEntity);
        return newChargeList.get(newChargeList.size()-1);
    }
}
