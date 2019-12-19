package com.jdbctemplate.util;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * 业务逻辑基类<br>
 * 可以根据切换 IDaoSupport 的实现切换数据库
 * @author wyj
 */
public abstract class BaseSupport<T> {
	protected final Logger logger = Logger.getLogger(getClass());
	@Resource protected IDaoSupport<T> daoSupport;    //TODO 拓展IDaoSupport实现方式，实现不同数据库的完美切换 daoSupport最好在配置文件中注册。
	@Resource protected JdbcTemplate jdbcTemplate;
	
}
