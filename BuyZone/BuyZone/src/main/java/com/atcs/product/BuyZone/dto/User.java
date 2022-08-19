package com.atcs.product.BuyZone.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@NoArgsConstructor
@AllArgsConstructor
public class User  {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;




    // one user can have many roles so it will be One to Many relation
    // jsonIgnore is used to avoid Circular Depenedency

    private Set<UserRole> userRoles = new HashSet<>();







}