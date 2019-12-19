package thirdparty.amap.bean;

/**
 * 坐标类
 * @author wyj
 *
 */
public class Coordinate {
    
    
    
    private Double longitude;
    private Double latitude;
    private String type;
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Coordinate(Double longitude,Double latitude,String type) {
        this.longitude=longitude;
        this.latitude=latitude;
        this.type=type;
    }
    public Coordinate() {
    }
    
}
