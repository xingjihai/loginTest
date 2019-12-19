//package com.atomikos;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.apache.log4j.Logger;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.atomikos.jdbc.AtomikosDataSourceBean;
//import com.jdbctemplate.util.IDaoSupport;
//import com.jdbctemplate.util.MysqlDaoSupport;
//
//
///**
// * 业务逻辑基类<br>
// * 可以根据切换 IDaoSupport 的实现切换数据库
// * @author wyj
// */
//public abstract class BaseSupport_ext<T> {
//	protected final Logger logger = Logger.getLogger(getClass());
//	protected IDaoSupport<T> daoSupport;    //TODO 拓展IDaoSupport实现方式，实现不同数据库的完美切换 daoSupport最好在配置文件中注册。
//	protected IDaoSupport<T> daoSupport_ext;    //TODO 拓展IDaoSupport实现方式，实现不同数据库的完美切换 daoSupport最好在配置文件中注册。
//	@Resource protected JdbcTemplate jdbcTemplate;
//	
//    public IDaoSupport<T> getDaoSupport() {
//        return daoSupport;
//    }
//    public void setDaoSupport(IDaoSupport<T> daoSupport) {
//        this.daoSupport = daoSupport;
//    }
//    public IDaoSupport<T> getDaoSupport_ext() {
//        return daoSupport_ext;
//    }
//    @Resource
//    public void setDaoSupport_ext(IDaoSupport<T> daoSupport_ext) {
//        MysqlDaoSupport my=(MysqlDaoSupport)daoSupport_ext;
//        com.atomikos.jdbc.AtomikosDataSourceBean datasource= (AtomikosDataSourceBean) my.getJdbcTemplate().getDataSource();
//      System.out.println("HibernateSimpleDaoInner:  ==========="+ datasource.getXaProperties().getProperty("url"));
//        this.daoSupport_ext = daoSupport_ext;
//        this.daoSupport = daoSupport_ext;       //在daoSupport_ext注入时设置值。使得代码中的变量不变，还是为daoSupport
//    }
//	
//	
//}
