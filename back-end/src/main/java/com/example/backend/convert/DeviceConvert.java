package com.example.backend.convert;

import com.example.backend.dao.DeviceDO;
import com.example.backend.dao.UserInfoDO;
import com.example.backend.vo.DeviceVO;
import com.example.backend.vo.UserInfoVo;

public class DeviceConvert {
    public static DeviceVO convertDO2VO(DeviceDO deviceDO) {
        if (deviceDO == null) {
            return null;
        }
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId(deviceDO.getDeviceId());
        deviceVO.setDeviceLat(deviceDO.getDeviceLat());
        deviceVO.setDeviceLng(deviceDO.getDeviceLng());
        deviceVO.setDeviceType(deviceDO.getDeviceType());
        deviceVO.setElementCas(deviceDO.getElementCas());
        deviceVO.setElementName(deviceDO.getElementName());
        deviceVO.setStorageHum(deviceDO.getStorageHum());
        deviceVO.setStorageLux(deviceDO.getStorageLux());
        deviceVO.setStorageTemp(deviceDO.getStorageTemp());
        return deviceVO;
    }
}
