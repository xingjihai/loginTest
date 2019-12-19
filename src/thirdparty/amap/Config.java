package thirdparty.amap;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

public class Config {
    public static final String KEY="c1b5f3ec13777ce61e80a5ec650df003";
    public static final String SIG_KEY="aaa97a79288627ec3a2312340c72a3e9"; //签名
    
    /**
     * 生成签名
     */
    public static String getSig(Map<String,Object> map){
        try {
            String[] array = new String[map.size()] ;
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            int a=0;
            for (String key : map.keySet()) {
                array[a]=key;
                a++;
            }
            Arrays.sort(array);
            for (int i = 0; i < map.size(); i++) {
                if(i==map.size()-1){
                    sb.append(array[i]+"="+map.get(array[i]));
                }else{
                    sb.append(array[i]+"="+map.get(array[i])+"&");
                }
            }
            sb.append(SIG_KEY);
            String str = sb.toString();
            // SHA1签名生成
            String sig=md5(str);
            return sig;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    
    public static String md5(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
