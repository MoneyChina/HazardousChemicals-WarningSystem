package com.example.backend.service;

import com.example.backend.dao.HumDataDO;

import java.util.List;

public interface DataService {
    List<HumDataDO> getHumData(Integer days, Integer deviceId);
}
