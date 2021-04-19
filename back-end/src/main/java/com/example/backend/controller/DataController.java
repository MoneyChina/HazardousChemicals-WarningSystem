package com.example.backend.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.dao.*;
import com.example.backend.req.DataMonitoringReq;
import com.example.backend.req.WarningListReq;
import com.example.backend.service.DataService;
import com.example.backend.service.DeviceService;
import com.example.backend.vo.DataDetailVO;
import com.example.backend.vo.DeviceDataVO;
import com.example.backend.vo.WarningRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    DeviceService deviceService;

    @PostMapping("/DeviceMonitoring")
    public DeviceDataVO getDeviceData(@RequestBody DataMonitoringReq dataMonitoringReq) {
//        System.out.println("device!!! "+deviceId);
        Random random = new Random();
        DeviceDataVO deviceDataVO = new DeviceDataVO();
        String elementName = deviceService.getNameByDeviceId(dataMonitoringReq.getDeviceId());
        Integer temp;
        Integer randomTemp = random.nextInt(100);
        System.out.println(elementName);
        if(elementName.equals("白磷")){
            if (randomTemp <= 92){
                temp = random.nextInt(20) + 15;
            }
            else{
                temp = random.nextInt(30) + 19;
            }
        }
        else{
            if (randomTemp <= 80){
                temp = random.nextInt(30) + 25;
            }
            else{
                temp = random.nextInt(900) + 60;
            }
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

    @PostMapping("/GetWarningRecord")
    public WarningRecordVO getWarningList(@RequestBody WarningListReq warningListReq){
        WarningRecordVO warningRecordVO = new WarningRecordVO();
        System.out.println("pre-date is "+warningListReq.getDate());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf1.format(new Date(Long.parseLong(warningListReq.getDate())));
        System.out.println("date is "+date);
        List<WarningInfoDO> warningInfoDOList = dataService.getWarningList(date);
        for(WarningInfoDO warningInfoDO:warningInfoDOList){
            JSONObject time;
            time = getDateJSON(warningInfoDO.getGmtCreate());
            warningInfoDO.setTime(time);
            if(warningInfoDO.getParam().equals("温度")){
                warningInfoDO.setValueText(warningInfoDO.getValue()+"°C");
            }else if(warningInfoDO.getParam().equals("湿度")){
                warningInfoDO.setValueText(warningInfoDO.getValue()+"%");
            }else{
                warningInfoDO.setValueText(warningInfoDO.getValue()+"lux");
            }
        }
        warningRecordVO.setWarningInfoDOList(warningInfoDOList);
        return warningRecordVO;
    }

    @PostMapping("/getWarningGeneral")
    public WarningGeneralDO getWarningGeneral(){
        return dataService.getWarningGeneral();
    }

    @PostMapping("/DataMonitoring")
    public DataDetailVO getDetailData(@RequestBody DataMonitoringReq dataMonitoringReq){
        DataDetailVO dataDetailVO = new DataDetailVO();
        List<HumDataDO> humDataDOList = null;
        List<TempDataDO> tempDataDOList = null;
        List<LuxDataDO> luxDataDOList = null;

        humDataDOList = dataService.getHumData(8, dataMonitoringReq.getDeviceId());
        tempDataDOList = dataService.getTempData(8, dataMonitoringReq.getDeviceId());
        luxDataDOList = dataService.getLuxData(8, dataMonitoringReq.getDeviceId());
        JSONArray humJsonArray = new JSONArray();
        JSONArray tempJsonArray = new JSONArray();
        JSONArray luxJsonArray = new JSONArray();

        List<DeviceDataDO> deviceDataDOList = new ArrayList<>();
        deviceDataDOList = dataService.getDataByDays(8, dataMonitoringReq.getDeviceId());
        for(DeviceDataDO deviceDataDO : deviceDataDOList){
            if(deviceDataDO!=null){
                JSONObject humJsonObject = new JSONObject();
                humJsonObject.put("value", deviceDataDO.getHum());
                Date date = deviceDataDO.getGmtCreate();
                JSONObject time;
                time = getDateJSON(date);
                humJsonObject.put("Time", time);
                humJsonArray.add(humJsonObject);

                humJsonObject = new JSONObject();
                humJsonObject.put("value", deviceDataDO.getTemp());
                date = deviceDataDO.getGmtCreate();
                time = getDateJSON(date);
                humJsonObject.put("Time", time);
                tempJsonArray.add(humJsonObject);

                humJsonObject = new JSONObject();
                humJsonObject.put("value", deviceDataDO.getLux());
                date = deviceDataDO.getGmtCreate();
                time = getDateJSON(date);
                humJsonObject.put("Time", time);
                luxJsonArray.add(humJsonObject);
            }
        }

//        for(HumDataDO humDataDO : humDataDOList){
//            JSONObject humJsonObject = new JSONObject();
//            humJsonObject.put("value", humDataDO.getHum());
//            Date date = humDataDO.getGmtCreate();
//            JSONObject time;
//            time = getDateJSON(date);
//            humJsonObject.put("Time", time);
//            humJsonArray.add(humJsonObject);
//        }
//        for(TempDataDO tempDataDO : tempDataDOList){
//            JSONObject humJsonObject = new JSONObject();
//            humJsonObject.put("value", tempDataDO.getTemp());
//            Date date = tempDataDO.getGmtCreate();
//            JSONObject time;
//            time = getDateJSON(date);
//            humJsonObject.put("Time", time);
//            tempJsonArray.add(humJsonObject);
//        }
//        for(LuxDataDO luxDataDO : luxDataDOList){
//            JSONObject humJsonObject = new JSONObject();
//            humJsonObject.put("value", luxDataDO.getLux());
//            Date date = luxDataDO.getGmtCreate();
//            JSONObject time;
//            time = getDateJSON(date);
//            humJsonObject.put("Time", time);
//            luxJsonArray.add(humJsonObject);
//        }
//        System.out.println(humJsonArray.toString());
        dataDetailVO.setHumData(humJsonArray);
        dataDetailVO.setTempData(tempJsonArray);
        dataDetailVO.setLuxData(luxJsonArray);
        return dataDetailVO;
    }

    private JSONObject getDateJSON(Date date){
        JSONObject Time = new JSONObject();
        String year = new SimpleDateFormat("yyyy").format(date);
        String month = new SimpleDateFormat("MM").format(date);
        String day = new SimpleDateFormat("dd").format(date);
        SimpleDateFormat d = new SimpleDateFormat("HH");
        d.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String hour = d.format(date);
        String minute = new SimpleDateFormat("mm").format(date);
        String second = new SimpleDateFormat("ss").format(date);
        Time.put("year", Integer.parseInt(year));
        Time.put("month", Integer.parseInt(month));
        Time.put("day", Integer.parseInt(day));
        Time.put("hour", Integer.parseInt(hour));
        Time.put("minute", Integer.parseInt(minute));
        Time.put("second", Integer.parseInt(second));
        return Time;
    }
}
