package com.grp08.capstoneprojectg08.repository;

import org.json.JSONObject;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public interface TransactionRepo {
    public boolean saveTransaction(JSONObject transactionObject);
}
