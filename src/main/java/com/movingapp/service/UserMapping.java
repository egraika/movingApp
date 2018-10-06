package com.movingapp.service;

import com.movingapp.Constants.MovingAppConstants;
import com.movingapp.entity.User;
import com.movingapp.view.UserView;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapping {

    @Autowired
    private UserService userService;

    public User UserViewToUser(UserView userView) {

        User userToMap = userService.findById(userView.getId());

        userToMap.setFirstName(userView.getFirstName());
        userToMap.setLastName(userView.getLastName());
        userToMap.setEmail(userView.getEmail());
        userToMap.setPhone(userView.getPhone());

        return userToMap;
    }

    public UserView UserToUserView(User user) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getEmail());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        userView.setPhone(user.getPhone());

        userView.setCcLastFour(user.getCcLastFour());
        userView.setCcExpirationDate(user.getCcExpirationDate());
        userView.setCardType(user.getCcCardType());

        if(user.getStripeCustomerID() != null) {
            Stripe.apiKey = MovingAppConstants.apiKey;
            Customer customer = Customer.retrieve(user.getStripeCustomerID());
            Card card = (Card) customer.getSources().retrieve(customer.getDefaultSource());
            userView.setCcLastFour(card.getLast4());
            userView.setCcExpirationDate(card.getExpMonth() + "/" + card.getExpYear());
            userView.setCardType(card.getBrand());
        }


        return userView;
    }
}
