package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.LuxDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface LuxDataMapper extends BaseMapper<LuxDataDO> {
    List<LuxDataDO> getLuxData(@PathVariable Integer day, @PathVariable Integer deviceId);
}
