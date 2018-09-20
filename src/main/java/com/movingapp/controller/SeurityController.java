package com.movingapp.controller;

import com.movingapp.entity.Token;
import com.movingapp.entity.Employee;
import com.movingapp.dao.TokenRepo;
import com.movingapp.dao.UserRepo;
import com.movingapp.config.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(description = "Users management API")
public class SeurityController {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private TokenRepo tokenRepo;

    @RequestMapping(value = "/security/account", method = RequestMethod.GET)
    public @ResponseBody
    Employee getUserAccount()  {
        Employee employee = userRepo.findByLogin(SecurityUtils.getCurrentLogin());
        employee.setPassword(null);
        return employee;
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
