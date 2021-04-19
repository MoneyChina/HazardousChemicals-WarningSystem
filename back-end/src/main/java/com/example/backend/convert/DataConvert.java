package com.example.backend.convert;

import com.example.backend.dao.DeviceDataDO;
import com.example.backend.vo.DeviceDataVO;

public class DataConvert {
    public static DeviceDataDO convertVO2DO(DeviceDataVO deviceDataVO){
        if(deviceDataVO == null){
            return null;
        }
        DeviceDataDO deviceDataDO = new DeviceDataDO();
        deviceDataDO.setDeviceId(deviceDataVO.getDeviceId());
        deviceDataDO.setHum(deviceDataVO.getHum());
        deviceDataDO.setLux(deviceDataVO.getLux());
        deviceDataDO.setTemp(deviceDataVO.getTemp());
        deviceDataDO.setGmtCreate(deviceDataVO.getGmtCreate());
        if(deviceDataVO.isRay()){
            deviceDataDO.setRay(1);
        } else {
            deviceDataDO.setRay(0);
        }
        if(deviceDataVO.isSmog()){
            deviceDataDO.setSmog(1);
        } else {
            deviceDataDO.setSmog(0);
        }
        return deviceDataDO;
    }
}
