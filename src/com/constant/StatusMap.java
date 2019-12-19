package com.constant;

import java.util.HashMap;
import java.util.Map;

public class StatusMap {
    /** 状态  */
    public static final Map<Integer,String> MediateStatusMap = new HashMap<>();
    
    static{
        MediateStatusMap.put(0, "待受理");
        MediateStatusMap.put(1, "取消调解");
        MediateStatusMap.put(2, "不建议调解");
        MediateStatusMap.put(3, "已受理待缴费");
        MediateStatusMap.put(4, "调解中");
        MediateStatusMap.put(5, "调解成功");
        MediateStatusMap.put(6, "调解失败");
    }
}
