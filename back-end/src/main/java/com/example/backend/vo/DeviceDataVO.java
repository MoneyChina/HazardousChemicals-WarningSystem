package com.example.backend.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类
 *
 * @author Zhou Lei
 * @Time 2021-04-13 14:44:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDataVO {
    private Integer temp;
    private Integer hum;
    private Integer lux;
    private boolean smog;
    private boolean ray;
    private JSONObject ac;
}
