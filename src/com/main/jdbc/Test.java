package com.main.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Test {
	
	
	public static void main(String[] args) {
		
		
//		testInsert();
		
		
		/** 31119 数据的结果：
		 * SELECT * FROM test
			use：196ms
			SELECT count(*) FROM test
			use：13ms
			SELECT count(1) FROM test
			use：10ms
		 */
		String countSql1="SELECT * FROM test";
//		String countSql2="SELECT count(*) FROM test";
//		String countSql3="SELECT count(1) FROM test";
//		String countSql4="SELECT * FROM test limit 1";
//		String countSql5="SELECT string FROM test limit 1";
//		testCount(countSql1);
//		testCount(countSql2);
//		testCount(countSql3);
//		testCount(countSql4);
//		testCount(countSql5);
		
		testQuery(countSql1);
	}
	
	public static void testCount(String countSql){
		Long time1=Calendar.getInstance().getTimeInMillis();
		JDBCTools2 jdbcTools=new JDBCTools2();
		ResultSet reset=jdbcTools.query(countSql);
		Long time2=Calendar.getInstance().getTimeInMillis();
		System.out.println(countSql+"\r\nuse："+(time2-time1)+"ms");
	}
	
	public static void testQuery(final String querySql){
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				public void run() {
					JDBCTools jdbcTools=new JDBCTools();
//					for (int j = 0; j < 10; j++) {
						try {
							jdbcTools.getConnection();
							List<Map<String, Object>> list=jdbcTools.Query(querySql,null);
							System.out.println(list);
						} catch (Exception e1) {
							e1.printStackTrace();
						}finally{
							try {
								jdbcTools.CloseConnection();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
//					}
				}
			}).start();
		}
		
	}
	
	public static void testInsert(){
		final String insertSql="INSERT INTO `test` ( `string`, `int`, `long`, `datetime`, `timestamp`) VALUES ( '字符串', '1', '24355434567864', '2017-12-01 00:00:00', '2017-12-01 00:00:00') ";
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				public void run() {
					JDBCTools2 jdbcTools=new JDBCTools2();
//						JdbcTemplateTools jdbcTemplateTools=new JdbcTemplateTools();
					for (int j = 0; j < 10; j++) {
						
						jdbcTools.save(insertSql);
//						jdbcTemplateTools.save(insertSql);
					}
				}
			}).start();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("final count="+JDBCTools2.count);
		
	}
	
}
