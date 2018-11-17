package com.hb.rbac.entity;

import com.hb.base.entity.AbstractRelationBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity(name = "rbac_user")
public class RbacUserEO extends AbstractRelationBaseEntity {

    private String userName;

    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
