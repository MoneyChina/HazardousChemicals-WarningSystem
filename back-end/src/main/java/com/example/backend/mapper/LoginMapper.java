package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.UserInfoDO;
import com.example.backend.model.LoginModel;
import com.example.backend.model.RegisterModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<UserInfoDO> {
    UserInfoDO getUserInfo(LoginModel loginModel);
    Integer register(RegisterModel registerModel);
}
