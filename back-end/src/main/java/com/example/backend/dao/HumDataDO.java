package com.example.backend.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("hum_data")
@Table(name="device")
public class HumDataDO {
    private Integer Id;
    private Integer deviceId;
    private Integer realTime;
    private Integer hum;
    private Date gmtCreate;

}
