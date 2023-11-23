package com.grp08.capstoneprojectg08.entity.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private int id;
    private String cardCode; // 15 Characters
    private String cardOwner;
    private String cvvCode; // 3 Characters
    private String dateExpire;

    public Card(){
    }
    public Card(String cardCode, String cardOwner, String cvvCode, String dateExpire){
        this.cardCode = cardCode;
        this.cardOwner = cardOwner;
        this.cvvCode = cvvCode;
        this.dateExpire = dateExpire;
    }

    public static boolean validateCardCode(String inputCardCode){
        return inputCardCode.length() == 15;
    }

    public static boolean validateCvvCode(String inputCvvCode){
        return inputCvvCode.length() == 3;
    }

}
