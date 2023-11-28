package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.repository.OrderRepo;
import com.grp08.capstoneprojectg08.repository.PaymentRepo;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.net.URL;

public class PaymentController extends BaseController{

    public PaymentController() {
        super();
    }

    // save payment, order, invoice to database (only when payment success)
    // clear current userSession's invoice
    public void savePayment(URL url){
        Invoice currentUserInvoice = UserSession.getInstance().getInvoice();
        OrderRepo.saveInvoice(currentUserInvoice);
        PaymentRepo.savePaymentTransaction(url);
        UserSession.getInstance().emptyInvoice();
    }
}
