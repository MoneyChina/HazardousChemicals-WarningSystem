package com.example.backend.req;

import lombok.Data;

@Data
public class ChangePwdReq {
    //用户名
    private String userName;
    //用户旧密码（会进行MD5加密）
    private String userOldPwd;
    //用户旧密码（会进行MD5加密）
    private String userNewPwd;
    //用户id(用于更新)
    private Integer userId;
}
