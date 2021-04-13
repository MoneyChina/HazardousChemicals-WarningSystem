package com.example.backend.controller;

import com.example.backend.req.DeleteDeviceReq;
import com.example.backend.service.DeviceService;
import com.example.backend.util.Result;
import com.example.backend.util.ResultUtil;
import com.example.backend.vo.DeviceVO;
import com.example.backend.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/getDeviceList")
    public Result getDeviceList() {
        try{
            List<DeviceVO> deviceVOList = deviceService.getDeviceList();
            return ResultUtil.success(deviceVOList);
        } catch (Exception e) {
            return ResultUtil.error(404, "查询失败！");
        }
    }

    @PostMapping("/deleteDevice")
    public Result deleteDevice(@RequestBody DeleteDeviceReq deleteDeviceReq) {
        System.out.println(deleteDeviceReq);
        if(deleteDeviceReq == null) {
            return ResultUtil.error(404, "删除请求为空！");
        }
        try{
            Integer result = deviceService.deleteDevice(deleteDeviceReq);
            return ResultUtil.success(result);
        } catch (Exception e) {
            return ResultUtil.error(404, "删除失败！");
        }
    }
}
