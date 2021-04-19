package com.example.backend.service;

import com.example.backend.dao.*;
import com.example.backend.vo.DeviceDataVO;

import java.util.Date;
import java.util.List;

public interface DataService {
    List<HumDataDO> getHumData(Integer days, Integer deviceId);
    List<TempDataDO> getTempData(Integer days, Integer deviceId);
    List<LuxDataDO> getLuxData(Integer days, Integer deviceId);
    void insertWarningRecord(WarningInfoDO warningInfoDO);
    DeviceDataDO getLastestData(Integer deviceId);
    void saveRecord(DeviceDataVO deviceDataVO);
    boolean todayDataExisted(Integer deviceId);
    List<DeviceDataDO> getDataByDays(Integer days, Integer deviceId);
    List<WarningInfoDO> getWarningList(String date);
    WarningGeneralDO getWarningGeneral();

}
