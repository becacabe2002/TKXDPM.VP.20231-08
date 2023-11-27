package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.entity.order.Invoice;
import com.grp08.capstoneprojectg08.repository.OrderRepo;
import com.grp08.capstoneprojectg08.repository.PaymentRepo;
import com.grp08.capstoneprojectg08.util.UserSession;

import java.net.URL;

public class PaymentController extends BaseController{
    OrderRepo orderRepo = new OrderRepo();
    PaymentRepo paymentRepo = new PaymentRepo();

    public PaymentController() {
        super();
    }

    // save payment, order, invoice to database (only when payment success)
    // clear current userSession's invoice
    public void savePayment(URL url){
        Invoice currentUserInvoice = UserSession.getInstance().getInvoice();
        orderRepo.saveInvoice(currentUserInvoice);
        paymentRepo.savePaymentTransaction(url);
        UserSession.getInstance().emptyInvoice();
    }
}
