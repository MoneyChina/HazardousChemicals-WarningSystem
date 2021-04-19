package com.example.backend.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.WarningInfoDO;
import com.example.backend.service.DataService;
import com.example.backend.service.DeviceService;
import com.example.backend.vo.DeviceDataVO;
import com.example.backend.vo.DeviceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Slf4j
public class WarningScheduleTask {
    @Autowired
    DeviceService deviceService;

    @Autowired
    DataService dataService;

    @Async
    @Scheduled(cron = "0/5 * * * * ?")  //间隔5秒
    public void generateData() throws InterruptedException{
        List<DeviceVO> deviceVOList = deviceService.getDeviceList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(sdf.format(new Date()) + ", 正在由线程： "+Thread.currentThread().getName()+" 读取数据中");
        for(DeviceVO deviceVO: deviceVOList){
            DeviceDataVO deviceDataVO = generateCustomize(deviceVO);
            if(deviceDataVO != null){
                Integer deviceId = deviceVO.getDeviceId();
                if((deviceDataVO.getTemp()>=deviceVO.getStorageTemp()) && deviceVO.getStorageTemp()!=-999){
                    String description = "预警中！由设备"+deviceId+"监测的危化品："+deviceVO.getElementName()+", 存储温度超过阈值，目前温度为"+deviceDataVO.getTemp()+"度，请检查！";
                    log.info(description);
                    WarningInfoDO warningInfoDO = extractDataIntoWarningDO(deviceVO, deviceDataVO, "温度", deviceDataVO.getTemp());
                    warningInfoDO.setDiscription(description);
                    dataService.insertWarningRecord(warningInfoDO);
                    continue;
                }
                if((deviceDataVO.getHum()>=deviceVO.getStorageHum()) && deviceVO.getStorageHum()!=-999){
                    String description = "预警中！由设备"+deviceId+"监测的危化品："+deviceVO.getElementName()+", 存储湿度超过阈值，目前湿度为"+deviceDataVO.getHum()+"%，请检查！";
                    log.info(description);
                    WarningInfoDO warningInfoDO = extractDataIntoWarningDO(deviceVO, deviceDataVO, "湿度", deviceDataVO.getHum());
                    warningInfoDO.setDiscription(description);
                    dataService.insertWarningRecord(warningInfoDO);
                    continue;
                }
                if((deviceDataVO.getLux()>=deviceVO.getStorageLux()) && deviceVO.getStorageLux()!=-999){
                    String description = "预警中！由设备"+deviceId+"监测的危化品："+deviceVO.getElementName()+", 存储光照强度超过阈值，目前光强为"+deviceDataVO.getLux()+"lux，请检查！";
                    log.info(description);
                    WarningInfoDO warningInfoDO = extractDataIntoWarningDO(deviceVO, deviceDataVO, "光照强度", deviceDataVO.getLux());
                    warningInfoDO.setDiscription(description);
                    dataService.insertWarningRecord(warningInfoDO);
                    continue;
                }
                boolean todayDataExisted = dataService.todayDataExisted(deviceId);
                deviceDataVO.setDeviceId(deviceId);
                deviceDataVO.setGmtCreate(new Date());
                if(!todayDataExisted){
                    dataService.saveRecord(deviceDataVO);
                }
            }
        }
    }

    private DeviceDataVO generateCustomize(DeviceVO deviceVO){
        DeviceDataVO deviceDataVO = new DeviceDataVO();
        Random random = new Random();
        Integer temp;
        Integer hum;
        Integer lux;
        Integer randomTemp = random.nextInt(100);
        switch (deviceVO.getElementName()){
            case "白磷":
                if (randomTemp <= 92){
                    temp = random.nextInt(20) + 15;
                }
                else{
                    temp = random.nextInt(30) + 19;
                }
                break;
            default:
                if (randomTemp <= 91){
                    temp = random.nextInt(20) + 10;
                }
                else{
                    temp = random.nextInt(23) + 12;
                }
        }
        if(deviceVO.getStorageHum()!=-999){
            if (randomTemp <= 92){
                hum = random.nextInt(20) + (deviceVO.getStorageHum() - 20 - 2);
            }
            else{
                hum = random.nextInt(20) + (deviceVO.getStorageHum() - 20 + 6);
            }
        }
        else{
            hum = random.nextInt(20) + 55;
        }
        if(deviceVO.getStorageLux()!=-999){
            if (randomTemp <= 92){
                lux = random.nextInt(deviceVO.getStorageLux() - 10);
            }
            else{
                lux = random.nextInt(deviceVO.getStorageLux() + 15);
            }
        }
        else{
            lux = random.nextInt(10000);
        }
        Integer xg = random.nextInt(3);
        Integer yg = random.nextInt(3);
        Integer zg = random.nextInt(3);
        boolean ray = false;
        boolean smog = false;
        Integer randomChoosePeople = random.nextInt(100);
        if(randomChoosePeople <= 10) {
            ray = true;
        }
        Integer randomChooseFire = random.nextInt(10000);
        if(randomChooseFire <= 3){
            smog = true;
        }
        deviceDataVO.setTemp(temp);
        deviceDataVO.setHum(hum);
        deviceDataVO.setLux(lux);
        deviceDataVO.setRay(ray);
        deviceDataVO.setSmog(smog);
        deviceDataVO.setTemp(temp);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xg", xg);
        jsonObject.put("yg", yg);
        jsonObject.put("zg", zg);
        deviceDataVO.setAc(jsonObject);
        return deviceDataVO;
    }
    private WarningInfoDO extractDataIntoWarningDO(DeviceVO deviceVO, DeviceDataVO deviceDataVO, String param, Integer value){
        WarningInfoDO warningInfoDO = new WarningInfoDO();
        warningInfoDO.setDeviceId(deviceVO.getDeviceId());
        warningInfoDO.setCnName(deviceVO.getElementName());
        warningInfoDO.setDeviceLat(deviceVO.getDeviceLat());
        warningInfoDO.setDeviceLng(deviceVO.getDeviceLng());
        warningInfoDO.setGmtCreate(new Date());
        warningInfoDO.setParam(param);
        if(value!=null){
            warningInfoDO.setValue(value);
        }
        return warningInfoDO;
    }
}
