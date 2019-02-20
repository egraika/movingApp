package com.movingapp.service;

import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserMapping {

    @Autowired
    private UserService userService;

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
        userView.setEnabled(user.getEnabled());
        userView.setLocations(user.getLocations());
        userView.setCreatedOn(user.getCreatedOn());
        if(user.getPicture() != null) {
            String base64Encoded = Base64.getEncoder().encodeToString(user.getPicture());
            userView.setPicture(base64Encoded);
        }

        String locationsArray = "";
        int i = 0;
        for(Location location : user.getLocations()) {
            if(i == 5) {
                locationsArray = locationsArray.substring(0,locationsArray.length()-1);
                locationsArray += "....";
                break;
            }
            locationsArray += location.getLocation() + ",";
            i++;
        }
        if(locationsArray.length() > 0) {
            locationsArray = locationsArray.substring(0,locationsArray.length()-1);
        }
        userView.setLocationsArray(locationsArray);

        return userView;
    }

    public List<UserView> UsersToUserViews(List<User> users) {

        List<UserView> userViews = new ArrayList<>();

        for(User user  : users) {
            UserView userView = new UserView();
            userView.setId(user.getId());
            userView.setEmail(user.getEmail());
            userView.setFirstName(user.getFirstName());
            userView.setLastName(user.getLastName());
            userView.setFullName(user.getFirstName() + " " + user.getLastName());
            userView.setPhone(user.getPhone());
            userView.setAuthorities(user.getAuthorities());
            userView.setCcLastFour(user.getCcLastFour());
            userView.setCcExpirationDate(user.getCcExpirationDate());
            userView.setCardType(user.getCcCardType());
            userView.setCharges(ChargeMapping.ChargeEntityToCharge(user.getCharges()));
            userView.setLocations(user.getLocations());
            userView.setEnabled(user.getEnabled());
            userView.setCreatedOn(user.getCreatedOn());
            if(user.getPicture() != null) {
                String base64Encoded = Base64.getEncoder().encodeToString(user.getPicture());
                userView.setPicture(base64Encoded);
            }

            String locationsArray = "";
            int i = 0;
            for(Location location : user.getLocations()) {
                if(i == 5) {
                    locationsArray = locationsArray.substring(0,locationsArray.length()-1);
                    locationsArray += "....";
                    break;
                }
                locationsArray += location.getLocation() + ",";
                i++;
            }
            if(locationsArray.length() > 0) {
                locationsArray = locationsArray.substring(0,locationsArray.length()-1);
            }
            userView.setLocationsArray(locationsArray);
            userViews.add(userView);
        }

        return userViews;
    }

    public List<User> UserViewsToUsers(List<UserView> userViews) {

        List<User> userList = new ArrayList<>();

        for(UserView userView  : userViews) {
            User user = new User();
            user.setId(userView.getId());
            userList.add(user);
        }

        return userList;
    }
}
