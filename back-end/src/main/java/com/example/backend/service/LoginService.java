package com.example.backend.service;

import com.example.backend.req.ChangePwdReq;
import com.example.backend.req.LoginReq;
import com.example.backend.req.RegisterReq;
import com.example.backend.vo.UserInfoVo;

public interface LoginService {
    UserInfoVo login(LoginReq loginReq);
    UserInfoVo register(RegisterReq registerReq);
    Integer changePwd(ChangePwdReq changePwdReq);
}
