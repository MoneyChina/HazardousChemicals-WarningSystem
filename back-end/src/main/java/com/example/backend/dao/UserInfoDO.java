package com.example.backend.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@TableName("user_info")
@Table(name="user_info")
public class UserInfoDO {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userID;
    private String userName;
    private String userPwd;
    private String userType;
}
