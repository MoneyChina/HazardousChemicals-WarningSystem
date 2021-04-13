package com.example.backend.controller;

import com.example.backend.req.ChangePwdReq;
import com.example.backend.req.LoginReq;
import com.example.backend.req.RegisterReq;
import com.example.backend.service.LoginService;
import com.example.backend.util.Result;
import com.example.backend.util.ResultUtil;
import com.example.backend.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

@RestController
@Slf4j
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginReq loginReq) {
        try{
            UserInfoVo result = loginService.login(loginReq);
            if(!"".equals(result.getUserName()) || null != result.getUserName()) {
                return ResultUtil.success(result);
            } else {
                return ResultUtil.error(404, "查询失败，请重新输入用户名和密码！");
            }
        } catch (Exception e) {
            return ResultUtil.error(404, "查询失败，请重新输入用户名和密码！");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterReq registerReq) {
        try {
            UserInfoVo result = loginService.register(registerReq);
            if(null == result) {
                return ResultUtil.error(404, "创建失败，请输入新的用户名！");
            } else {
                return ResultUtil.success(result);
            }
        } catch (Exception e) {
            log.info("SQL执行失败，详情为："+e.getMessage());
            return ResultUtil.error(404, "创建失败！账户名已被占用，请更换用户名！");
        }
    }

    @PostMapping("/changePwd")
    public Result changePwd(@RequestBody ChangePwdReq changePwdReq) {
        try {
            Integer result = loginService.changePwd(changePwdReq);
            if (-1 == result) {
                return ResultUtil.error(404, "创建失败，用户名或密码有误，请重试！");
            } else {
                return ResultUtil.success(result);
            }
        } catch (Exception e) {
            log.info("SQL执行失败，详情为："+e.getMessage());
            return ResultUtil.error(404, "修改失败！用户名或密码有误，请重试！");
        }
    }
}
