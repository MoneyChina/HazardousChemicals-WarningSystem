package com.example.backend.req;

import lombok.Data;

@Data
public class LoginReq {
    //用户名
    private String userName;
    //用户密码（会进行MD5加密）
    private String userPwd;
}
