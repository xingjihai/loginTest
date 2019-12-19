package thirdparty.amap.bean;

/** 
 * 坐标类型 
 */  
public enum CoordinateType {  
    
  
    AMAP("amap"),   //高德
    BAIDU("baidu"),   //百度
    GPS("gps"),   //gps
    MAPBAR("mapbar");    //mapbar
    
    private String coordinateType = "";  
  
    CoordinateType(String coordinateType) {  
        this.coordinateType = coordinateType;  
    }  
  
    /** 
     * @return the msgType 
     */  
    @Override  
    public String toString() {  
        return coordinateType;  
    }  
}  
