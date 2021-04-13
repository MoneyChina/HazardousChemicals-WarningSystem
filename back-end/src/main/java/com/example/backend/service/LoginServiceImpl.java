package com.example.backend.service;

import com.example.backend.convert.LoginConvert;
import com.example.backend.convert.RegisterConvert;
import com.example.backend.dao.UserInfoDO;
import com.example.backend.mapper.LoginMapper;
import com.example.backend.model.LoginModel;
import com.example.backend.model.RegisterModel;
import com.example.backend.req.ChangePwdReq;
import com.example.backend.req.LoginReq;
import com.example.backend.req.RegisterReq;
import com.example.backend.util.EncodeUtils;
import com.example.backend.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginMapper loginMapper;

    @Override
    public UserInfoVo login(LoginReq loginReq) {
        LoginModel loginModel;
        loginModel = LoginConvert.convertReq2Model(loginReq);
        UserInfoDO result = loginMapper.getUserInfo(loginModel);
        UserInfoVo userInfoVo = LoginConvert.convertDO2VO(result);
        if (null == result) {
            return null;
        }
        return userInfoVo;
    }

    @Override
    public UserInfoVo register(RegisterReq registerReq) {
        UserInfoDO userInfoDO;
        userInfoDO = RegisterConvert.convertReq2DO(registerReq);
        Integer result = loginMapper.insert(userInfoDO);
        if(result > 0) {
            UserInfoVo userInfoVo;
            userInfoVo = LoginConvert.convertDO2VO(userInfoDO);
            return userInfoVo;
        }
        return null;
    }

    @Override
    public Integer changePwd(ChangePwdReq changePwdReq) {
        LoginModel loginModel;
        loginModel = LoginConvert.convertReq2Model(changePwdReq);
        UserInfoDO result = loginMapper.getUserInfo(loginModel);
        if(null == result) {
            return -1;
        } else {
            changePwdReq.setUserId(result.getUserID());
            UserInfoDO userInfoDO;
            userInfoDO = LoginConvert.convertReq2DO(changePwdReq);
            Integer output = loginMapper.updateById(userInfoDO);
            return output;
        }
    }
}
