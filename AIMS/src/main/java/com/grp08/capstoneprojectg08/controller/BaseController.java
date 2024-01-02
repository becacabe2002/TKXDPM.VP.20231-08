package com.grp08.capstoneprojectg08.controller;

import com.grp08.capstoneprojectg08.service.*;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class BaseController {
    protected MediaService mediaService = new MediaService();
    protected CartService cartService = new CartService();

    protected OrderService orderService = new OrderService();
    protected PaymentService paymentService = new PaymentService();
    protected UserService userService = new UserService();
}
