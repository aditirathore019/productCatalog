package com.atcs.product.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    // Many to One Mapping b/w Userrole and user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // Many to one Mapping b/w Userrole and role
    // this established Many to Many Relation b/w role and user as Userrole is Many to one with
    // both user and role tables so they are Mapped in Many to Many Relation now
    @ManyToOne
    private Role role;





    public UserRole(User user, Role role) {
        super();
        this.user = user;
        this.role = role;
    }

    public UserRole() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



}
