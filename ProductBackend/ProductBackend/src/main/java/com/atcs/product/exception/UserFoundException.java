package com.atcs.product.exception;

public class UserFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserFoundException() {
        super("user already Exists!");

    }

    public UserFoundException(String msg) {
        super(msg);
    }

}