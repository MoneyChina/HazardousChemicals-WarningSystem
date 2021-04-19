package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.DeviceDataDO;
import com.example.backend.dao.WarningGeneralDO;
import com.example.backend.dao.WarningInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper extends BaseMapper<DeviceDataDO> {
    DeviceDataDO getData(Integer deviceId);
    DeviceDataDO getTodayData(Integer deviceId);
    List<DeviceDataDO> getDataByDays(Integer days, Integer deviceId);
    List<WarningInfoDO> getWarningList(String date);
    Integer getWarningGeneralByDay();
    Integer getWarningGeneralByMonth();
    Integer getWarningGeneralByWeek();
}
