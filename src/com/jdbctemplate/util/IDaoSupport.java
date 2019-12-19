package com.jdbctemplate.util;

import java.util.List;
import java.util.Map;


/**
 * 数据库操作支撑接口
 */
public interface IDaoSupport<T> {
    
    // *************************  常用操作 start **********************// 
    /**
     * 新增数据
     * @param po 要新增的对象，保证对象的属性名和字段名对应
     */
    public T insert(T po);
    
    /**
     * 删除数据
     */
    public void delete(Object key,Class<T> clazz);
    
    /**
     * 更新数据
     * @param po 要更新的对象，保证对象的属性名和字段名对应
     * @param where 更新条件(字段-值Map)
     */
    public void update(T  po, Map where);
    
    /**
     * 更新数据
     * @param po 要更新的对象，保证对象的属性名和字段名对应
     * @param where 更新条件,如"a='1' AND b='2'"
     * this.daoSupport.update( member, "member_id="+member.getMember_id());
     */
    public void update(T po, String where) ;
    
    /**
     * 查询单一结果集<br/>
     * 并将结果转为<code>T</code>对象返回
     * @param id 主键
     */
    public  T queryObjectByKey(Object key,Class<T> clazz);
    /**
     * 以对象作为查询条件(不是很规范) -- 查询多行结果集<br/>
     * 并将结果转为<code>T</code>对象返回
     * @param po  条件对象（必须为实体对象 --保证对象的属性名和字段名对应）
     * @param orderSql 排序字符串 如：" order by create_time desc "
     */
    public List<T> queryListByClass(T po,String orderSql,Class<T> clazz);
    
    /**
     * 以对象作为查询条件(不是很规范) --分页查询
     * @param po  条件对象（必须为实体对象 --保证对象的属性名和字段名对应）
     * @param pageNo 查询的起始页
     * @param pageSize 每页数量
     * @param orderSql 排序字符串 如：" order by create_time desc "
     */
    public Page queryForPageByClass(T  po,int pageNo, int pageSize,String orderSql,Class<T> clazz);
    
    
    // *************************  常用操作 end **********************// 
    
    
    
    /**执行sql语句**/
    public void execute(String sql, Object... args) ;
    
    /**
     * 获取当前事务最后一次更新的主键值
     */
    public int getLastId(String table);
    
    /**
     * 新增数据
     * @param table  表名
     * @param fields 字段-值Map
     */
    public void insert(String table, Map fields);
        
    /**
     * 更新数据
     * @param table  表名
     * @param fields 字段-值Map
     * @param where 更新条件,如"a='1' AND b='2'"
     */
//    public void update(String table, Map fields, String where);
    
    /**
     * 查询单一结果集<br/>
     * 并将结果转为<code>K</code>对象返回
     * @param sql 查询的sql语句,确保结果列名和对象属性对应
     * @param clazz  <code>K</code>的Class对象
     * @param args 对应sql语句中的参数值
     * @return
     */
    public <K> K queryForObject(String sql, Class<K> clazz, Object... args);
    
   
   /**
    * 查询多行结果集<br/>
    * 并将结果转为<code>T</code>对象返回
    * @param table  表名
    * @param clazz  <code>T</code>的Class对象
    * @param mapWhere 条件
    * @return
    */
//   public <T> List<T> queryByObject( Class<T> clazz, Map whereMap);
    
    
    /**
     * 查询多行结果集<br/>
     * 并将结果转为<code>List<K></code>
     * @param sql 查询的sql语句
     * @param clazz <code><T></code>的Class对象
     * @param args 对应sql语句中的参数值
     * @return  列表中元素为<code>K</code>的<code>List</code>
     */
    public <K> List<K> queryForList(String sql, Class<K> clazz, Object... args);
    
    
    /**
     * 查询多行结果集<br/>
     * 并将结果转为<code>List<Map></code>
     * @param sql 查询的sql语句
     * @param args 对应sql语句中的参数值
     * @return  列表中元素为<code>Map</code>的<code>List</code>,<br/>Map结构：以结果集中的列为key，值为value,
     */
    public List<Map<String, Object>> queryForList(String sql, Object... args);
    
    
    /**
    *  分页查询
    * @param sql 查询的sql语句
    * @param pageNo 查询的起始页
    * @param pageSize 每页数量
    * @param clazz  <code><K></code>的Class对象
    * @param args 对应sql语句中的参数值
    */
    public <K> Page queryForPage(String sql, int pageNo, int pageSize, Class<K> clazz, Object... args);
    
    
}
