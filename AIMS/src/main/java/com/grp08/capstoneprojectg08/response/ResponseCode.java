package com.grp08.capstoneprojectg08.response;

/**
 * @author <a href="https://github.com/becacabe2002">becacabe2002</a>
 */
public class ResponseCode {
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;

    public static final int UNAUTHORIZED = 401;
    public static final int NOT_FOUND = 404;

    /**
     * Response code for failed transaction
     */
    public static final int UNACCEPTABLE = 406;
    public static final int ITEM_ALREADY_EXIST = 409;
}
