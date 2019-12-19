package com.jdbctemplate.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.Assert;

import com.jdbctemplate.Member;

import net.sf.json.JSONObject;

public class JdbcUtil {
    /**
     * 标识不是数据库读写的字段(放get上)
     */
    @Retention(RetentionPolicy.RUNTIME) 
    @Target(ElementType.FIELD) 
    public @interface NotDbField {

    }
    /**
     * 标识不是数据库读写的字段 --当key自增时的标识（可以不用）
     */
    @Retention(RetentionPolicy.RUNTIME) 
    @Target(ElementType.FIELD) 
    public @interface PrimaryKey {
    }
    
    /**
     * 对应数据库表
     */
    @Retention(RetentionPolicy.RUNTIME) 
    @Target(ElementType.TYPE) 
    public @interface Table {
        String table();
        String key();
    }
    
    
    
    /**
     * 将数组成str连接成字符串
     * 
     * @param str
     * @param array
     * @return
     */
    public static String implode(String str, Object[] array) {
        if (str == null || array == null) {
            return "";
        }
        String result = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                result += array[i].toString();
            } else {
                result += array[i].toString() + str;
            }
        }
        return result;
    }

    public static String implodeValue(String str, Object[] array) {
        if (str == null || array == null) {
            return "";
        }
        String result = "";
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                result += "?";
            } else {
                result += "?" + str;
            }
        }
        return result;
    }

    /**
     * 格式化列名 只适用于Mysql
     * 
     * @param col
     * @return
     */
    public static String quoteCol(String col) {
        if (col == null || col.equals("")) {
            return "";
        } else {
            // if("2".equals(EopSetting.DBTYPE))//Oracle
            // return "\"" + col + "\"";
            // else if("1".equals(EopSetting.DBTYPE))//mysql
            // return "`" + col + "`";
            // else //mssql
            // return "[" + col + "]";
            return col;
        }
    }
    
    /**
     * 格式化值 只适用于Mysql
     * @param value
     * @return
     */
    public static String quoteValue(String value) {
        if (value == null || value.equals("")) {
            return "''";
        } else {
            return "'" + value.replaceAll("'", "''") + "'";
        }
    }
   
    
    
    

    public static void main(String[] args) {
        Member member=new Member();
        member.setMember_id(1);
        member.setUsername("用户1");
        member.setPassword("密码");
        member.setNofield("field");
        Map<Object, Object> map=po2Map(member,true);
        System.out.println(JSONObject.fromObject(map).toString());
    }

    /**
     * 将po对象中有属性和值转换成map  过滤空值***
     * 
     * @param po  实体类
     * @param isRemoveField  是否除去非数据库读写字段
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static  Map<Object, Object> po2Map(Object po,boolean isRemoveField) {
        Map poMap = new HashMap();
        Map map = new HashMap();
        try {
             map = BeanUtils.describe(po);
        } catch (Exception ex) {
        }
        
        //过滤Map中的class和空值
        Object[] keyArray = map.keySet().toArray();
        for (int i = 0; i < keyArray.length; i++) {   
            String str = keyArray[i].toString();
            if (str != null && !str.equals("class")) {
                if (map.get(str) != null) {
                    poMap.put(str, map.get(str));
                }
            }
        }
        if(isRemoveField){
            //除去非数据库读写字段
            Field fields[]=po.getClass().getDeclaredFields();    
            for (Field field : fields) {
                if( field.getAnnotation(NotDbField.class)!=null || field.getAnnotation(PrimaryKey.class)!=null ){
                    poMap.remove(field.getName());
                }
            }
        }
        return poMap;
    }
    
    
    public static String buildPageSql(String sql, int page, int pageSize) {

        String sql_str = null;

        String db_type = MysqlDaoSupport.DBTYPE;
        if (db_type.equals("1")) {
            db_type = "mysql";
        } else if (db_type.equals("2")) {
            db_type = "oracle";
        } else if (db_type.equals("3")) {
            db_type = "sqlserver";
        }

        if (db_type.equals("mysql")) {
            sql_str = sql + " LIMIT " + (page - 1) * pageSize + "," + pageSize;
        } else if (db_type.equals("oracle")) {
            StringBuffer local_sql = new StringBuffer("SELECT * FROM (SELECT t1.*,rownum sn1 FROM (");
            local_sql.append(sql);
            local_sql.append(") t1) t2 WHERE t2.sn1 BETWEEN ");
            local_sql.append((page - 1) * pageSize + 1);
            local_sql.append(" AND ");
            local_sql.append(page * pageSize);
            sql_str = local_sql.toString();
        } else if (db_type.equals("sqlserver")) {
            StringBuffer local_sql = new StringBuffer();
            // 找到order by 子句
            String order = findOrderStr(sql);

            // 剔除order by 子句
            if (order != null)
                sql = removeOrders(sql);
            else
                order = "order by id desc"; // SQLServer分页必需有order by
                                            // 子句，如果默认语句不包含order
                                            // by，自动以id降序，如果没有id字段会报错

            // 拼装分页sql
            local_sql.append("select * from (");
            local_sql.append(insertSelectField("ROW_NUMBER() Over(" + order + ") as rowNum", sql));
            local_sql.append(") tb where rowNum between ");
            local_sql.append((page - 1) * pageSize + 1);
            local_sql.append(" AND ");
            local_sql.append(page * pageSize);

            return local_sql.toString();

        }

        return sql_str.toString();

    }
    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     * 
     * @see #pagedQuery(String,int,int,Object[])
     */
    public static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }
    /**
     * 去除sql的select 子句，未考虑union的情况,用于pagedQuery.
     * 
     * @see #pagedQuery(String,int,int,Object[])
     */
    public  static String removeSelect(String sql) {
        // 不明白之前的修改什么意思，还原包含group by的sql处理
        // Author chopper 2016-06-28
        sql = sql.toLowerCase();
        if (sql.indexOf("group by") != -1) {
            return " from (" + sql + ") temp_table";
        }

        // FIXME 当查询中含有函数，比如SUM(),替换SQL出错
        Pattern p = Pattern.compile("\\(.*\\)", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            int c = m.end() - m.start();
            m.appendReplacement(sb, getStr(c, "~"));
        }
        m.appendTail(sb);

        String replacedSql = sb.toString();

        int index = replacedSql.indexOf("from");

        // 如果不存在
        if (index == -1) {
            index = replacedSql.indexOf("FROM");
        }
        return sql.substring(index);
    }
    /*
     * private String removeSelect(String hql) { Assert.hasText(hql); int
     * beginPos = hql.toLowerCase().lastIndexOf("from"); Assert.isTrue(beginPos
     * != -1, " hql : " + hql + " must has a keyword 'from'");
     * 
     * return hql.substring(beginPos); }
     */
    public static String getStr(int num, String str) {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    /**
     * 从一个sql语句中找到order by 子句
     * @param sql
     * @return
     */
    public static  String findOrderStr(String sql ){

        String pattern = "(order\\s*by[\\w|\\W|\\s|\\S]*)";
        Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
        Matcher m = p.matcher(sql);

        if (m.find()) {
            return m.group();
         
        } 
        return null;
    }
    
    public static  String insertSelectField(String field,String sql){
        sql = "select " + field+","+sql.substring(6, sql.length());
        return sql;
    }
}
