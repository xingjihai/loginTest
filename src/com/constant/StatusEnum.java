package com.constant;

public enum StatusEnum{
    status_0(0, "待受理"),
    status_1(1, "取消调解"),
    status_2(2, "不建议调解"),
    status_3(3, "已受理待缴费"),
    status_4(4, "调解中"),
    status_5(5, "调解成功"),
    status_6(6, "调解失败");
    private int code;
    private String value;
    private StatusEnum(int code,String value){
        this.code=code;
        this.value=value;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * 根据code获取value
     */
    public String getByCode(Integer code){
        StatusEnum[] status=StatusEnum.values();
        for (StatusEnum sta : status) {
            if(sta.getCode()==code){
                return sta.getValue();
            }
        }
        return "";
    }
}
