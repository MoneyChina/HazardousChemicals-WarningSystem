package com.example.backend.model;

import lombok.Data;

@Data
public class LoginModel {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userType;
}
