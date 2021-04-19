package com.example.backend.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@TableName("temp_data")
@Table(name="temp_data")
public class TempDataDO {
    private Integer Id;
    private Integer deviceId;
    private Integer realTime;
    private Integer temp;
    private Date gmtCreate;
}
