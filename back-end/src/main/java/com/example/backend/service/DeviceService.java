package com.example.backend.service;

import com.example.backend.req.DeleteDeviceReq;
import com.example.backend.vo.DeviceVO;

import java.util.List;

public interface DeviceService {
    List<DeviceVO> getDeviceList();
    Integer deleteDevice(DeleteDeviceReq deleteDeviceReq);
}
