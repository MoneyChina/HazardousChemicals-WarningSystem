package com.example.backend.vo;

import com.example.backend.dao.WarningInfoDO;
import lombok.Data;

import java.util.List;
@Data
public class WarningRecordVO {
    List<WarningInfoDO> warningInfoDOList;
}
