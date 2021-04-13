package com.example.backend.convert;

import com.example.backend.dao.UserInfoDO;
import com.example.backend.model.LoginModel;
import com.example.backend.req.ChangePwdReq;
import com.example.backend.req.LoginReq;
import com.example.backend.util.EncodeUtils;
import com.example.backend.vo.UserInfoVo;

public class LoginConvert {
    public static LoginModel convertReq2Model(LoginReq loginReq) {
        if (null == loginReq) {
            return null;
        }
        String userName = loginReq.getUserName();
        System.out.println(loginReq.getUserName() + " " + loginReq.getUserPwd());
        String encode = EncodeUtils.md5(loginReq.getUserPwd());
        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(userName);
        loginModel.setUserPwd(encode);
        return loginModel;
    }
    public static LoginModel convertReq2Model(ChangePwdReq changePwdReq) {
        if (null == changePwdReq) {
            return null;
        }
        String userName = changePwdReq.getUserName();
        String encode = EncodeUtils.md5(changePwdReq.getUserOldPwd());
        LoginModel loginModel = new LoginModel();
        loginModel.setUserName(userName);
        loginModel.setUserPwd(encode);
        return loginModel;
    }

    public static UserInfoDO convertReq2DO(ChangePwdReq changePwdReq) {
        if (null == changePwdReq) {
            return null;
        }
        String username = changePwdReq.getUserName();
        String encode = EncodeUtils.md5(changePwdReq.getUserNewPwd());
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserName(username);
        userInfoDO.setUserPwd(encode);
        userInfoDO.setUserID(changePwdReq.getUserId());
        return userInfoDO;
    }

    public static UserInfoVo convertDO2VO(UserInfoDO userInfoDO) {
        if (null == userInfoDO) {
            return null;
        }
        String userName = userInfoDO.getUserName();
        Integer userId = userInfoDO.getUserID();
        String userType = userInfoDO.getUserType();
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserName(userName);
        userInfoVo.setUserID(userId);
        userInfoVo.setUserType(userType);
        return userInfoVo;
    }
}
