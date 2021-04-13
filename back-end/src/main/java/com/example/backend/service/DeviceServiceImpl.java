package com.example.backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.convert.DeviceConvert;
import com.example.backend.dao.DeviceDO;
import com.example.backend.dao.UserInfoDO;
import com.example.backend.mapper.DeviceMapper;
import com.example.backend.req.DeleteDeviceReq;
import com.example.backend.vo.DeviceVO;
import com.example.backend.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public List<DeviceVO> getDeviceList() {
        QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
        List<DeviceDO> deviceDOList = new ArrayList<>();
        deviceDOList = deviceMapper.selectList(queryWrapper);
        List<DeviceVO> deviceVOList = new ArrayList<>();
        for(DeviceDO deviceDO : deviceDOList) {
            DeviceVO deviceVO;
            deviceVO = DeviceConvert.convertDO2VO(deviceDO);
            deviceVOList.add(deviceVO);
        }
        System.out.println(deviceVOList);
        return deviceVOList;
    }

    @Override
    public Integer deleteDevice(DeleteDeviceReq deleteDeviceReq) {
        return deviceMapper.deleteById(deleteDeviceReq.getDeviceId());
    }
}
