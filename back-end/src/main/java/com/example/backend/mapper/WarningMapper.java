package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.dao.WarningInfoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarningMapper extends BaseMapper<WarningInfoDO> {

}
