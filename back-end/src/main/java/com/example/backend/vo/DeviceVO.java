package com.example.backend.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class DeviceVO {
    //设备ID
    private Integer deviceId;
    //设备经度
    private BigDecimal deviceLng;
    //设备纬度
    private BigDecimal deviceLat;
    //监控危化品品名
    private String elementName;
    //传感器类型名
    private String deviceType;
    //监控危化品CAS号
    private String elementCas;
    //监控危化品适宜存放温度
    private Integer storageTemp;
    //监控危化品适宜存放湿度
    private Integer storageHum;
    //监控危化品适宜存放光照强度
    private Integer storageLux;

}
