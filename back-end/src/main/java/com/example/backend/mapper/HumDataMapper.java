package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.HumDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface HumDataMapper extends BaseMapper<HumDataDO> {
    List<HumDataDO> getHumData(@PathVariable Integer day, @PathVariable Integer deviceId);
}
