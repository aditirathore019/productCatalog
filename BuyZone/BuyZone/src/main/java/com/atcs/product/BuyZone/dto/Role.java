package com.atcs.product.BuyZone.dto;

import java.util.HashSet;
import java.util.Set;




public class Role {

    private Long roleId;
    private String roleName;

    // now role will have one to many relation with the userRole as well

    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }







    public Role(Long roleId, String roleName) {
        super();
        this.roleId = roleId;
        this.roleName = roleName;

    }







    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }



    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }





}
