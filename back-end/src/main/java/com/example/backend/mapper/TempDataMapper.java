package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.TempDataDO;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface TempDataMapper extends BaseMapper<TempDataDO> {
    List<TempDataDO> getTempData(@PathVariable Integer day, @PathVariable Integer deviceId);
}
