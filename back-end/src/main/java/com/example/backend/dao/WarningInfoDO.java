package com.example.backend.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@TableName("warning_list")
@Table(name="warning_list")
public class WarningInfoDO {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer deviceId;
    private String cnName;
    private Integer value;
    private String valueText;
    private Date gmtCreate;
    private String discription;
    private String param;
    //设备经度
    private BigDecimal deviceLng;
    //设备纬度
    private BigDecimal deviceLat;
    private JSONObject time;
}
