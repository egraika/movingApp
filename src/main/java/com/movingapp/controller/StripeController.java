package com.movingapp.controller;

import com.movingapp.model.ChargeEntity;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.MoveMapping;
import com.movingapp.view.ChargeView;
import com.movingapp.view.Move;
import com.movingapp.view.StripeView;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
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

        List<ChargeView> newChargeList = moveMapping.ChargeEntityToCharge(newChargeEntity);
        return newChargeList.get(newChargeList.size()-1);
    }
}
