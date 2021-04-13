package com.example.backend.convert;

import com.example.backend.dao.UserInfoDO;
import com.example.backend.model.RegisterModel;
import com.example.backend.req.RegisterReq;
import com.example.backend.util.EncodeUtils;
import com.example.backend.vo.UserInfoVo;

public class RegisterConvert {
    public static UserInfoDO convertReq2DO(RegisterReq registerReq) {
        if(registerReq == null) {
            return null;
        }
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserName(registerReq.getUserName());
        String encode = EncodeUtils.md5(registerReq.getUserPwd());
        userInfoDO.setUserPwd(encode);
        userInfoDO.setUserType("admin");
        return userInfoDO;
    }

}
