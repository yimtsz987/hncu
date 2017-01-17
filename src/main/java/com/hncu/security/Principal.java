package com.hncu.security;


import com.hncu.entity.User;

import java.io.Serializable;

/**
 * 授权用户对象
 */
public class Principal implements Serializable {
    private static final long serialVersionUID = -3474237226178990224L;

    private String id;
    private String name;
    private String username;

    public Principal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
