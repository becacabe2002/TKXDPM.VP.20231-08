package com.grp08.capstoneprojectg08.repository;

public interface BaseRepo {
    DeliveryRepo deliveryRepo = new DeliveryRepoImplement();
    ImageRepo imageRepo = new ImageRepoImplement();
    MediaRepo mediaRepo = new MediaRepoImplement();
    OrderRepo orderRepo = new OrderRepoImplement();
    PaymentRepo paymentRepo = new PaymentRepoImplement();
    UserRepo userRepo = new UserRepoImplement();
}
