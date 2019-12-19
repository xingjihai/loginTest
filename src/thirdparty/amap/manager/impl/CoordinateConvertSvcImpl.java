package thirdparty.amap.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.stereotype.Service;

import thirdparty.amap.Config;
import thirdparty.amap.bean.Coordinate;
import thirdparty.amap.bean.CoordinateType;
import thirdparty.amap.manager.CoordinateConvertSvc;
import tools.http.HttpTools;

/**
 * 坐标转换
 * http://lbs.amap.com/api/webservice/guide/api/convert#convert
 * @author wyj
 */
@Service
public class CoordinateConvertSvcImpl implements CoordinateConvertSvc{
    
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Coordinate> convertToAmap(List<Coordinate> list,String type) {
        StringBuffer url=new StringBuffer("http://restapi.amap.com/v3/assistant/coordinate/convert?");
        
        //    http://restapi.amap.com/v3/assistant/coordinate/convert?locations=116.481499,39.990475&coordsys=gps&output=xml&key=<用户的key>
        String locations="";
        for (int i = 0; i < list.size(); i++) {
            if(i==list.size()-1){
                locations+=list.get(i).getLongitude()+","+list.get(i).getLatitude();
            }else{
                locations+=list.get(i).getLongitude()+","+list.get(i).getLatitude()+"|";
            }
        }
        url.append("locations=").append(locations);
        url.append("&coordsys=").append(type);
        url.append("&output=").append("json");
        url.append("&key=").append(Config.KEY);
        
        Map paramsMap=new HashMap<String, Object>();
        paramsMap.put("locations", locations);
        paramsMap.put("coordsys", type );
        paramsMap.put("output","json");
        paramsMap.put("key", Config.KEY);
        String sig=Config.getSig(paramsMap);
        
        url.append("&sig=").append(sig);
        JSONObject return_obj=JSONObject.fromObject(  HttpTools.exceteGet(url.toString()) );
        String[] coordinate_str= return_obj.get("locations").toString().split(";");
        List<Coordinate>  newList=new ArrayList<>();
        for (String str: coordinate_str) {
            String[] lng_lat=str.split(",");
            Coordinate coordinate=new Coordinate( Double.parseDouble(lng_lat[0]), Double.parseDouble(lng_lat[1]) ,type);
            newList.add(coordinate);
        }
        return newList;
    }
    
    @Override
    public Coordinate convertToAmap(Double longitude, Double latitude, String type) {
        Coordinate cd=new Coordinate();
        cd.setLongitude(longitude);
        cd.setLatitude(latitude);
        List<Coordinate> coordList=new ArrayList<>();
        coordList.add(cd);
        List<Coordinate> list=convertToAmap(coordList,type);
        return list.get(0);
    }
    
    @Test
    public void Test(){
        //116.481499,39.990475|116.481499,39.990375
        
        Coordinate cd=new Coordinate();
        cd.setLongitude(116.481499);
        cd.setLatitude(39.990475);
        Coordinate cd2=new Coordinate();
        cd.setLongitude(116.481499);
        cd.setLatitude(39.990375);
        List coordList=new ArrayList<>();
        coordList.add(cd);
        coordList.add(cd);
        convertToAmap(coordList,CoordinateType.GPS.toString());  
    }
    @Test
    public void Test2(){
        Coordinate c=convertToAmap(116.481499,39.990475,CoordinateType.GPS.toString());
        System.out.println( JSONObject.fromObject(c).toString() );
    }

    
}
