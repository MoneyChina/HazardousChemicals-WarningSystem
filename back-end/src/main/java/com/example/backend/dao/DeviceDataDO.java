package com.example.backend.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("device_data")
@Table(name="device_data")
public class DeviceDataDO {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer deviceId;
    private Date gmtCreate;
    private Integer temp;
    private Integer hum;
    private Integer lux;
    private Integer smog;
    private Integer ray;
}
