package thirdparty.amap.manager;

import java.util.List;

import thirdparty.amap.bean.Coordinate;

/**
 * 坐标转换
 * @author wyj
 */
public interface CoordinateConvertSvc {
    
    /**
     * 将各种坐标转换成高德地图坐标 --多点转换
     */
    public List<Coordinate> convertToAmap(List<Coordinate> list,String type);
    /**
     * 将各种坐标转换成高德地图坐标 --单点转换
     */
    public Coordinate convertToAmap(Double longitude,Double latitude,String type);
}
