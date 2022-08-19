package com.atcs.product.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String authority;

    public Authority(String authority) {
        super();
        this.authority = authority;
    }

    public Authority() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
