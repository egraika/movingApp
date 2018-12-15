package com.movingapp.controller;

import com.movingapp.config.SecurityUtils;
import com.movingapp.dao.TokenRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.entity.Token;
import com.movingapp.entity.User;
import com.movingapp.service.UserMapping;
import com.movingapp.view.UserView;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Api(description = "Users management API")
public class SeurityController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private UserMapping userMapping;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody UserView getUserAccount() throws UnsupportedEncodingException {
        User user = userRepo.findByEmail(SecurityUtils.getCurrentLogin());
        if(user != null) {
            return userMapping.UserToUserView(user);
        } else {
            return null;
        }
    }


    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/security/tokens", method = RequestMethod.GET)
    public @ResponseBody
    List<Token> getTokens () {
        List<Token> tokens = tokenRepo.findAll();
        for(Token t:tokens) {
            t.setSeries(null);
            t.setValue(null);
        }
        return tokens;
    }



}
