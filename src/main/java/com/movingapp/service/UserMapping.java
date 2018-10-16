package com.movingapp.service;

import com.movingapp.entity.User;
import com.movingapp.view.UserView;
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
        //userToMap.setEmail(userView.getEmail());
        userToMap.setPhone(userView.getPhone());

        return userToMap;
    }

    public UserView UserToUserView(User user) {

        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getEmail());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        userView.setPhone(user.getPhone());
        userView.setAuthorities(user.getAuthorities());

        userView.setCcLastFour(user.getCcLastFour());
        userView.setCcExpirationDate(user.getCcExpirationDate());
        userView.setCardType(user.getCcCardType());
        userView.setCharges(ChargeMapping.ChargeEntityToCharge(user.getCharges()));

        return userView;
    }
}
