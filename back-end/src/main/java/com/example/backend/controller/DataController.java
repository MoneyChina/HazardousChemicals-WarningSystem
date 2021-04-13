package com.example.backend.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.HumDataDO;
import com.example.backend.service.DataService;
import com.example.backend.vo.DataDetailVO;
import com.example.backend.vo.DeviceDataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 类
 *
 * @author Zhou Lei
 * @Time 2021-04-13 15:15:34
 */
@RestController
@Slf4j
@RequestMapping("/Data")
@CrossOrigin
public class DataController {
    @Autowired
    DataService dataService;

    @PostMapping("/DeviceMonitoring")
    public DeviceDataVO getDeviceData(Integer deviceId) {
        Random random = new Random();
        DeviceDataVO deviceDataVO = new DeviceDataVO();
        Integer temp;
        Integer randomTemp = random.nextInt(100);
        if (randomTemp <= 80){
            temp = random.nextInt(30) + 25;
        }
        else{
            temp = random.nextInt(900) + 60;
        }
        Integer hum = random.nextInt(20) + 40;
        Integer lux = random.nextInt(10000);
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

    @PostMapping("/DataMonitoring")
    public DataDetailVO getDeatilData(@RequestBody Integer deviceId){
        DataDetailVO dataDetailVO = new DataDetailVO();
        List<HumDataDO> humDataDOList = null;
        humDataDOList = dataService.getHumData(8, deviceId);
        JSONArray humJsonArray = new JSONArray();

        for(HumDataDO humDataDO : humDataDOList){
            JSONObject humJsonObject = new JSONObject();
            humJsonObject.put("value", humDataDO.getHum());
            JSONObject Time = new JSONObject();
            Date date = humDataDO.getGmtCreate();
            String year = new SimpleDateFormat("yyyy").format(date);
            String month = new SimpleDateFormat("MM").format(date);
            String day = new SimpleDateFormat("dd").format(date);
            String hour = new SimpleDateFormat("HH").format(date);
            String minute = new SimpleDateFormat("mm").format(date);
            String second = new SimpleDateFormat("ss").format(date);
            Time.put("year", Integer.parseInt(year));
            Time.put("month", Integer.parseInt(month));
            Time.put("day", Integer.parseInt(day));
            Time.put("hour", Integer.parseInt(hour));
            Time.put("minute", Integer.parseInt(minute));
            Time.put("second", Integer.parseInt(second));
            humJsonObject.put("Time", Time);
            humJsonArray.add(humJsonObject);
        }
        System.out.println(humJsonArray.toString());
        dataDetailVO.setHumData(humJsonArray);
        return dataDetailVO;
    }
}
