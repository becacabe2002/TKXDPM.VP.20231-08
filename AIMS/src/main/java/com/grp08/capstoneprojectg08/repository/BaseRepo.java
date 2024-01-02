package com.grp08.capstoneprojectg08.repository;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public interface BaseRepo {
    DeliveryRepo deliveryRepo = new DeliveryRepoImplement();
    MediaRepo mediaRepo = new MediaRepoImplement();
    OrderRepo orderRepo = new OrderRepoImplement();
    PaymentRepo paymentRepo = new PaymentRepoImplement();
    UserRepo userRepo = new UserRepoImplement();
}
