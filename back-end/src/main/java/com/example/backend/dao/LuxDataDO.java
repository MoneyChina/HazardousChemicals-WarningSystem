package com.example.backend.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("lux_data")
@Table(name="lux_data")
public class LuxDataDO {
    private Integer Id;
    private Integer deviceId;
    private Integer realTime;
    private Integer lux;
    private Date gmtCreate;
}
