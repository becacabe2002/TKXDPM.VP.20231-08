package com.grp08.capstoneprojectg08.entity.payment;

public class Card {
    private int id;
    private String cardCode; // 15 Characters

    // private String bankName;
    private String cardOwner;
    private String cvvCode; // 3 Characters
    private String dateRelease;

    // private String dateExpire;

    public Card(){
    }
    public Card(String cardCode, String cardOwner, String cvvCode, String dateRelease){
        this.cardCode = cardCode;
        this.cardOwner = cardOwner;
        this.cvvCode = cvvCode;
        this.dateRelease = dateRelease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public static boolean validateCardCode(String inputCardCode){
        return inputCardCode.length() == 15;
    }

    public static boolean validateCvvCode(String inputCvvCode){
        return inputCvvCode.length() == 3;
    }

}
