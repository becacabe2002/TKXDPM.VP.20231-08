package com.grp08.capstoneprojectg08.service;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public interface PaymentStrategy {
    JSONObject processPayment();

    JSONObject savePayment(JSONObject result);
}
