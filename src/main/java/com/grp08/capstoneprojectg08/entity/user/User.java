package com.grp08.capstoneprojectg08.entity.user;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class User {
    private String uname;
    private UserRole userRole;
//    private String password;
    private UUID externalUID;
    private List<OrderHistory> orderHistories = null;

    public User(){
    }
    public User(String uname, UUID externalUID, String role){
        this.uname = uname;
//        this.password = password;
        this.externalUID = externalUID;
        this.userRole = UserRole.valueOf(role);
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UUID getExternalUID() {
        return externalUID;
    }

    public void setExternalUID(UUID externalUID) {
        this.externalUID = externalUID;
    }

    public List<OrderHistory> getOrderHistories() {
        return orderHistories;
    }

    public void setOrderHistories(List<OrderHistory> orderHistories) {
        this.orderHistories = orderHistories;
    }

    public void genUID(){
        this.externalUID = UUID.randomUUID();
    }

}
