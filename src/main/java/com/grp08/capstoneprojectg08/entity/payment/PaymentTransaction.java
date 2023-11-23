package com.grp08.capstoneprojectg08.entity.payment;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PaymentTransaction {
    private int invoiceId;
    private int cardId;

    private String content;

    private String method;
    private Timestamp createdAt;

    public PaymentTransaction(){
    }
}
