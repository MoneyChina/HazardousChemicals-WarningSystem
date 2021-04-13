package com.example.backend.service.impl;

import com.example.backend.dao.HumDataDO;
import com.example.backend.mapper.HumDataMapper;
import com.example.backend.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    HumDataMapper humDataMapper;

    @Override
    public List<HumDataDO> getHumData(Integer days, Integer deviceId) {
        List<HumDataDO> humDataDOList = new ArrayList<>();
        humDataDOList = humDataMapper.getHumData(8, deviceId);
        return humDataDOList;
    }
}
