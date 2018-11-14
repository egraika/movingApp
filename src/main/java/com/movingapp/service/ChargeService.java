package com.movingapp.service;

import com.movingapp.dao.ChargesDao;
import com.movingapp.model.ChargeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chargeService")
public class ChargeService {

    @Autowired
    private ChargesDao chargesDao;

    public ChargeEntity findById(int id) {
        return chargesDao.findById(id).get();
    }
}
