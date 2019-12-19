package com.beetlsql;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DefaultNameConversion;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

public class CreateTools {
    public static void main(String[] args) throws Exception {
        String driver = "com.p6spy.engine.spy.P6SpyDriver";
        String url = "jdbc:mysql://localhost:3306/login_test";
        String userName = "root";
        String password = "123456";
        
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/com/beetlsql/sql 目录下
        SQLLoader loader = new ClasspathLoader("/com/beetlsql/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
//        UnderlinedNameConversion nc = new  UnderlinedNameConversion();
        DefaultNameConversion nc = new  DefaultNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
        
        
        /**
         * 根据数据库表 在控制台中打印出类和sql模板
         */
//        sqlManager.genPojoCodeToConsole("member");
        sqlManager.genSQLTemplateToConsole("member");
    }
}
