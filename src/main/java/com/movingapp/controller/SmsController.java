package com.movingapp.controller;

import com.movingapp.dao.BookMovesDao;
import com.movingapp.model.MoveEntity;
import com.movingapp.service.SmsService;
import com.movingapp.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    BookMovesDao bookMovesDao;

    @RequestMapping(value = "/textMoveInformation", method = RequestMethod.POST ,consumes = "application/json")
    public ResponseEntity updateMove(@RequestBody List<UserView> userViews, @RequestParam("moveid") int moveid) {

        MoveEntity moveEntity = bookMovesDao.findById(moveid);
        smsService.sendSMS(userViews, moveEntity);

        return new ResponseEntity(HttpStatus.OK);
    }
}
