package com.atcs.product.exception;

public class UserNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("User Not Found");
        // TODO Auto-generated constructor stub
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }


}
