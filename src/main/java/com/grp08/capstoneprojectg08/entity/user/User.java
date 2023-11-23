package com.grp08.capstoneprojectg08.entity.user;

import com.grp08.capstoneprojectg08.entity.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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
    public void genUID(){
        this.externalUID = UUID.randomUUID();
    }

}
