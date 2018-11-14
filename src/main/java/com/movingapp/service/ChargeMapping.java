package com.movingapp.service;

import com.movingapp.entity.User;
import com.movingapp.model.ChargeEntity;
import com.movingapp.view.ChargeView;

import java.util.ArrayList;
import java.util.List;

public class ChargeMapping {

    public static List<ChargeView> ChargeEntityToCharge(List<ChargeEntity> chargeEntityList) {

        List<ChargeView> chargeList = new ArrayList<ChargeView>();

        if(chargeEntityList != null) {
            for(int i = 0; i < chargeEntityList.size(); i++) {
                ChargeView charge = new ChargeView();
                charge.setAmount(chargeEntityList.get(i).getAmount());
                charge.setType(chargeEntityList.get(i).getType());
                charge.setDate(chargeEntityList.get(i).getDate());
                charge.setID(chargeEntityList.get(i).getID());
                charge.setChargeid(chargeEntityList.get(i).getChargeid());
                charge.setMoveid(chargeEntityList.get(i).getMoveid());
                chargeList.add(charge);
            }
        }

        return chargeList;
    }

    public static List<ChargeEntity> ChargeToChargeEntity(List<ChargeView> chargeList, User user) {

        List<ChargeEntity> chargeEntityList = new ArrayList<ChargeEntity>();

        for(int i = 0; i < chargeList.size(); i++) {
            ChargeEntity charge = new ChargeEntity();
            charge.setAmount(chargeList.get(i).getAmount());
            charge.setType(chargeList.get(i).getType());
            charge.setDate(chargeList.get(i).getDate());
            charge.setID(chargeList.get(i).getID());
            charge.setUser(user);
            charge.setChargeid(chargeList.get(i).getChargeid());
            charge.setMoveid(chargeList.get(i).getMoveid());
            chargeEntityList.add(charge);
        }

        return chargeEntityList;
    }
}
