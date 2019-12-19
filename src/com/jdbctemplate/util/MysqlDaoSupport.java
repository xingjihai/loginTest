package com.jdbctemplate.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.jdbctemplate.util.JdbcUtil.Table;

/**
 * jdbc数据库操作支撑 (只在mysql中测试过)
 * @author wyj
 */
//@Service     ---在配置文件中注册，以便实现不同数据的切换只需要在配置文件中进行即可
public class MysqlDaoSupport<T> implements IDaoSupport<T> {
    //数据库类型
    public static String DBTYPE ="1" ; //1是mysql 2为oracle 3为sqlserver
    
    private JdbcTemplate jdbcTemplate;
    protected final Logger logger = Logger.getLogger(getClass());
    
    private String getDataTable(T po){
        String table=po.getClass().getAnnotation(Table.class).table();
        if(StringUtils.isBlank(table)){
            throw new DBRuntimeException("为给类"+po.getClass().getName()+"添加数据库表映射@Table");
        }
        return table;
    }
    private String getDataTable(Class<T> clazz){
        String table=clazz.getAnnotation(Table.class).table();
        if(StringUtils.isBlank(table)){
            throw new DBRuntimeException("为给类"+clazz.getName()+"添加数据库表映射@Table");
        }
        return table;
    }
    @SuppressWarnings("unchecked")
    private Class<T> getDataClass(T po){
        return (Class<T>) po.getClass();
    }
    
    public T insert(T po) {
        String table=getDataTable(po);
        insert(table, JdbcUtil.po2Map(po,true));
        
        //获取最后插入的对象
        Integer id=this.getLastId(table);
        return this.queryObjectByKey(id.toString(), getDataClass(po));
    }
    
    public void delete(Object key,Class<T> clazz){
        String primaryKey=clazz.getAnnotation(Table.class).key();
        String table=this.getDataTable(clazz);
        String sql="DELETE FROM "+table + "  WHERE "+ primaryKey+"=?";
        this.jdbcTemplate.update(sql, key);
    }
    
    
    public void update(T po, Map where) {
        String table=getDataTable(po);
        String whereSql = "";
        // where值
        if (where != null) {
            Object[] wherecols = where.keySet().toArray();
            for (int i = 0; i < wherecols.length; i++) {
                wherecols[i] = JdbcUtil.quoteCol(wherecols[i].toString()) + "=" + JdbcUtil.quoteValue(where.get(wherecols[i]).toString());
            }
            whereSql += JdbcUtil.implode(" AND ", wherecols);
        }
        update(table, JdbcUtil.po2Map(po,true), whereSql);
    }
    
    public void update( T po, String where) {
        String table=getDataTable(po);
        this.update(table, JdbcUtil.po2Map(po,true), where);
    }
    
    public T queryObjectByKey(Object key,Class<T> clazz){
        String primaryKey=clazz.getAnnotation(Table.class).key();
        String table=this.getDataTable(clazz);
        String sql="SELECT * FROM "+table + "  WHERE "+ primaryKey+"=?";
        return this.queryForObject(sql, clazz, key);
    }
    
    public List<T> queryListByClass(T po,String orderSql,Class<T> clazz) {
        Map<Object, Object> whereMap=JdbcUtil.po2Map(po, false);
        return queryByObject( whereMap ,orderSql,clazz);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Page queryForPageByClass(T po,int pageNo, int pageSize ,String orderSql,Class<T> clazz){
        Map<Object, Object> whereMap=JdbcUtil.po2Map(po, false);
        String sql="";
        String table=this.getDataTable(clazz);
        try {
            StringBuffer whereSql =new StringBuffer();
            ArrayList params=new ArrayList();
            // where值
            if (whereMap != null) {
                Object[] wherecols = whereMap.keySet().toArray();
                for (int i = 0; i < wherecols.length; i++) {
//                wherecols[i] = JdbcUtil.quoteCol(wherecols[i].toString()) + "=" + JdbcUtil.quoteValue(whereMap.get(wherecols[i]).toString());
                    params.add( whereMap.get(wherecols[i]).toString() );
                    wherecols[i] = JdbcUtil.quoteCol(wherecols[i].toString()) + "= ? " ;
                }
                if(wherecols.length>0){
                    whereSql.append(" WHERE ");
                }
                whereSql.append(  JdbcUtil.implode(" AND ", wherecols)   ) ;
            }
            orderSql=StringUtils.isBlank(orderSql)?"":" "+orderSql;
            sql = "SELECT * FROM " + table + whereSql +orderSql;
            
            return this.queryForPage(sql, pageNo, pageSize, clazz ,  params.toArray(new Object[params.size()]));
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    /**
     * 更新数据
     * @param table  表名
     * @param fields 字段-值Map
     * @param where 更新条件,如"a='1' AND b='2'"
     */
    private void update(String table, Map fields, String where) {
        String sql = "";
        try {
            Assert.hasText(table, "表名不能为空");
            Assert.notEmpty(fields, "字段不能为空");
            Assert.hasText(where, "where条件不能为空");
            table = JdbcUtil.quoteCol(table);

            // 字段值
            Object[] cols = fields.keySet().toArray();

            Object[] values = new Object[cols.length];
            for (int i = 0; i < cols.length; i++) {
                if (fields.get(cols[i]) == null) {
                    values[i] = null;
                } else {
                    values[i] = fields.get(cols[i]).toString();
                }
                cols[i] = JdbcUtil.quoteCol(cols[i].toString()) + "=?";

            }
            sql = "UPDATE " + table + " SET " + JdbcUtil.implode(", ", cols) + " WHERE " + where;
            this.jdbcTemplate.update(sql, values);
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    public void execute(String sql, Object... args) {
        try {
            this.jdbcTemplate.update(sql, args);
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    public int getLastId(String table) {
        if (DBTYPE.equals("1")) {
            return queryForInt("SELECT last_insert_id() as id");
        } else if (DBTYPE.equals("2")) {
            int result = 0;
            result = queryForInt("SELECT s_" + table + ".currval as id from DUAL");
            return result;
        } else if (DBTYPE.equals("3")) {
            int result = 0;
            result = queryForInt("select @@identity");
            return result;

        }
        throw new RuntimeException("未知的数据库类型");
    }
    
    
    public void insert(String table, Map fields) {
        String sql = "";
        try {
            Assert.hasText(table, "表名不能为空");
            Assert.notEmpty(fields, "字段不能为空");
            table = JdbcUtil.quoteCol(table);
            Object[] cols = fields.keySet().toArray();
            Object[] values = new Object[cols.length];
            for (int i = 0; i < cols.length; i++) {
                if (fields.get(cols[i]) == null) {
                    values[i] = null;
                } else {
                    values[i] = fields.get(cols[i]);
                }
                cols[i] = JdbcUtil.quoteCol(cols[i].toString());
            }
            sql = "INSERT INTO " + table + " (" + JdbcUtil.implode(", ", cols);
            sql = sql + ") VALUES (" + JdbcUtil.implodeValue(", ", values);
            sql = sql + ")";
            jdbcTemplate.update(sql, values);
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    

    public <K> K queryForObject(String sql, Class<K> clazz, Object... args){
        List<K> list=this.queryForList(sql, clazz, args);
        if(list.size()>0){
            if(list.size()>1){
                throw new DBRuntimeException("数据库查询结果不唯一", sql);
            }
            return list.get(0);
        }else{
            return null;
        }
    }
    
    
    /**
     * 查询单一结果集<br/>
     * 并将结果转为<code>T</code>对象返回
     * @param po  对象
     * @param mapWhere 条件
     * @param orderSql  排序字符串 如：" order by create_time desc "
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private  List<T> queryByObject( Map whereMap,String orderSql,Class<T> clazz){
        String sql="";
        String table=this.getDataTable(clazz);
        try {
            StringBuffer whereSql =new StringBuffer();
            ArrayList params=new ArrayList();
            // where值
            if (whereMap != null) {
                Object[] wherecols = whereMap.keySet().toArray();
                for (int i = 0; i < wherecols.length; i++) {
//                wherecols[i] = JdbcUtil.quoteCol(wherecols[i].toString()) + "=" + JdbcUtil.quoteValue(whereMap.get(wherecols[i]).toString());
                    params.add( whereMap.get(wherecols[i]).toString() );
                    wherecols[i] = JdbcUtil.quoteCol(wherecols[i].toString()) + "= ? " ;
                }
                if(wherecols.length>0){
                    whereSql.append(" WHERE ");
                }
                whereSql.append(  JdbcUtil.implode(" AND ", wherecols)   ) ;
            }
            orderSql=StringUtils.isBlank(orderSql)?"":" "+orderSql;
            sql = "SELECT * FROM " + table + whereSql+orderSql;
            return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>( clazz ), params.toArray(new Object[params.size()]));
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    public <K> List<K> queryForList(String sql, Class<K> clazz, Object... args){
        try {
            return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<K>(clazz), args);
        } catch (DataAccessException e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return this.jdbcTemplate.queryForList(sql, args);
    }

    
    
    public <K> Page queryForPage(String sql, int pageNo, int pageSize, Class<K> clazz, Object... args){
        try {
            Assert.hasText(sql, "SQL语句不能为空");
            Assert.isTrue(pageNo >= 1, "pageNo 必须大于等于1");
            String listSql = JdbcUtil.buildPageSql(sql, pageNo, pageSize);
//        String countSql = "SELECT COUNT(*) " + JdbcUtil.removeSelect(JdbcUtil.removeOrders(sql));
            String countSql = "SELECT COUNT(1) from (" + sql + ") a";
            List<K> list=this.queryForList(listSql, clazz, args);
            int totalCount = queryForInt(countSql, args);
            return new Page(pageNo, totalCount, pageSize, list);
        } catch (Exception e) {
            throw new DBRuntimeException(e, sql);
        }
    }
    
    private Integer queryForInt(String sql, Object... args) {
        try {
            Integer value = jdbcTemplate.queryForObject(sql, Integer.class, args);
            return  value==null?0:value;
        }catch(EmptyResultDataAccessException e){
            return 0;
        } catch (RuntimeException e) {
            this.logger.error(e.getMessage(), e);
            throw e;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
}
