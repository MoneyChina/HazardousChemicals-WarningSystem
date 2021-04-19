package com.example.backend.service.impl;

import com.example.backend.convert.DataConvert;
import com.example.backend.dao.*;
import com.example.backend.mapper.*;
import com.example.backend.service.DataService;
import com.example.backend.vo.DeviceDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    HumDataMapper humDataMapper;
    @Autowired
    TempDataMapper tempDataMapper;
    @Autowired
    LuxDataMapper luxDataMapper;
    @Autowired
    WarningMapper warningMapper;
    @Autowired
    DataMapper dataMapper;

    @Override
    public List<HumDataDO> getHumData(Integer days, Integer deviceId) {
        List<HumDataDO> humDataDOList = new ArrayList<>();
        humDataDOList = humDataMapper.getHumData(8, deviceId);
        return humDataDOList;
    }

    @Override
    public List<TempDataDO> getTempData(Integer days, Integer deviceId) {
        List<TempDataDO> tempDataDOList = null;
        tempDataDOList = tempDataMapper.getTempData(days, deviceId);
        return tempDataDOList;
    }

    @Override
    public List<LuxDataDO> getLuxData(Integer days, Integer deviceId) {
        List<LuxDataDO> luxDataDOList = null;
        luxDataDOList = luxDataMapper.getLuxData(days, deviceId);
        return luxDataDOList;
    }

    @Override
    public void insertWarningRecord(WarningInfoDO warningInfoDO) {
        Integer result = warningMapper.insert(warningInfoDO);
    }

    @Override
    public DeviceDataDO getLastestData(Integer deviceId) {
        DeviceDataDO deviceDataDO = dataMapper.getData(deviceId);
        return deviceDataDO;
    }

    @Override
    public void saveRecord(DeviceDataVO deviceDataVO) {
        DeviceDataDO deviceDataDO = DataConvert.convertVO2DO(deviceDataVO);
        Integer result = dataMapper.insert(deviceDataDO);
    }

    @Override
    public boolean todayDataExisted(Integer deviceId) {
        DeviceDataDO deviceDataDO = dataMapper.getData(deviceId);
        if(deviceDataDO != null){
            return true;
        }
        return false;
    }

    @Override
    public List<DeviceDataDO> getDataByDays(Integer days, Integer deviceId) {
        List<DeviceDataDO> deviceDataDOList = new ArrayList<>();
        deviceDataDOList = dataMapper.getDataByDays(days, deviceId);
        return deviceDataDOList;
    }

    @Override
    public List<WarningInfoDO> getWarningList(String date) {
        List<WarningInfoDO> warningInfoDOList = dataMapper.getWarningList(date);
        return warningInfoDOList;
    }

    @Override
    public WarningGeneralDO getWarningGeneral() {
        Integer resultDay = dataMapper.getWarningGeneralByDay();
        Integer resultWeek = dataMapper.getWarningGeneralByWeek();
        Integer resultMonth = dataMapper.getWarningGeneralByMonth();
        WarningGeneralDO warningGeneralDO = new WarningGeneralDO();
        warningGeneralDO.setThisday(resultDay);
        warningGeneralDO.setThisWeek(resultWeek);
        warningGeneralDO.setThisMonth(resultMonth);
        return warningGeneralDO;
    }


}
