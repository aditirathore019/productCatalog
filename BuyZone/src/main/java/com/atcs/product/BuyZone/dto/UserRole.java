package com.atcs.product.BuyZone.dto;



public class UserRole {

    private Long userRoleId;

    // Many to One Mapping b/w Userrole and user

    private User user;

    // Many to one Mapping b/w Userrole and role
    // this established Many to Many Relation b/w role and user as Userrole is Many to one with
    // both user and role tables so they are Mapped in Many to Many Relation now

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
