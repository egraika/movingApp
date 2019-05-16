package com.movingapp.service;

import com.movingapp.model.MoveEntity;
import com.movingapp.view.UserView;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SmsService {

    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACe3eb40edc79d76405c02b663e0d88b15";
    public static final String AUTH_TOKEN = "77021cebb1c9911ce29f654eb4f4e8e1";

    public void sendSMS(List<UserView> userViews, MoveEntity move) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        String formatDateTime = move.getMoveStart().format(formatter);
        String textMessage;
        if(move.getUser() != null) {
            textMessage = "You have been assigned to a move on " + formatDateTime + ". Customer Name: " + move.getUser().getFirstName() + " Phone: " + move.getUser().getPhone() + ". Please Look up other move details online.";
        } else {
            String [] arrOfStr = move.getAdminCreatedUser().split(",");
            String firstName = arrOfStr[0];
            String phone = arrOfStr[3];
            textMessage = "You have been assigned to a move on " + formatDateTime + ". Customer Name: " + firstName + " Phone: " + phone + ". Please Look up other move details online.";
        }
        for(UserView user : userViews) {
            try {
                Message message = Message.creator(new PhoneNumber("+1" + user.getPhone()),
                        new PhoneNumber("+19522326046"),
                        textMessage).create();
            } catch (Exception e){

            }
        }

    }
}
