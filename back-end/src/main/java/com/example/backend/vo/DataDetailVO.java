package com.example.backend.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataDetailVO {
    private JSONArray tempData;
    private JSONArray humData;
    private JSONArray luxData;
}
