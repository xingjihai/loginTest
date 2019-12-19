/*
 * @(#) Constant.java  1.0  2014
 *
 * Copyright 2016  by fivesheep Corporation.
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * fivesheep Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with fivesheep.
 */

package com.main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTools {
	private  String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
	private  String driverClassName = "com.mysql.jdbc.Driver";
//	private  String driverClassName = "com.p6spy.engine.spy.P6SpyDriver";
    private   String user = "root";
    private   String password = "admin";

	private  PreparedStatement PRST = null; 										// 创建一个 PreparedStatement
																						// 对象来将参数化的 SQL 语句发送到数据库
	public  Connection CONN = null; 												// 连接
	private  ResultSet RS = null; 												// 结果集

	/**
	 * 获取连接 线程同步
	 * 
	 * @return
	 */
	public  synchronized void getConnection() {
		try {
			Class.forName(driverClassName); // 加载驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			CONN = DriverManager.getConnection(url, user, password); // 装载连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接(Connection/PerpartdStatement/ResultSet) 内部调用
	 * @throws SQLException
	 */
	public  void CloseConnection() throws SQLException {
		if (null != RS) {
			RS.close();
		}
		if (null != PRST) {
			PRST.close();
		}
		if (null != CONN) {
			CONN.close();
		}
	}

	/**
	 * 增删改 参数：SQL语句、SQL参数表 返回值：受影响的行数
	 */
	public  Integer Update(String sql, List args) {
		Integer result = 0;

//		getConnection(); // 打开数据库连接
		try {
			PRST = CONN.prepareStatement(sql);// 载入SQL预编译语句
			SetValues(args, PRST); // 设置SQL的参数
			result = PRST.executeUpdate(); // 执行 SQL 更新
			System.out.println("数据库操作： "+sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			try {
//				CloseConnection(); // 关闭数据库连接
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return result;
	}

	/**
	 * 查询 参数：SQL语句、SQL参数表 返回值：查询的结果集(List<Map<String, Object>>:每一个Map对象存储一行数据)
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public  List<Map<String, Object>> Query(String sql, List args) {
		List<Map<String, Object>> result = null;
//		getConnection(); // 打开数据库连接

		try {
			PRST = CONN.prepareStatement(sql); // 载入SQL预编译语句
			SetValues(args, PRST);
			RS = PRST.executeQuery(); // 执行语句
		} catch (SQLException e) {
			System.out.println("Query error.");
			e.printStackTrace();
		}
		try {
			result = new ArrayList<Map<String, Object>>();
			ResultSetMetaData rsm = RS.getMetaData(); // 获取此 ResultSet
														// 对象的列的编号、类型和属性
			Map<String, Object> map = null;

			while (RS.next()) {
				map = new HashMap<String, Object>();
				for (int i = 1; i <= rsm.getColumnCount(); ++i)
					// getColumnCount() 返回此 ResultSet 对象中的列数
					map.put(rsm.getColumnName(i),
							RS.getObject(rsm.getColumnName(i)));
				result.add(map); // 将返回的每一行数据添进List
			}
		} catch (SQLException e) {
			System.out.println("...");
			e.printStackTrace();
		} finally {
//			try {
//				CloseConnection(); // 关闭数据库连接
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return result;
	}

	/**
	 * 装载SQL参数 参数：SQL参数表,装载了SQL预编译语句的对象
	 * 
	 * @param args
	 */
	private static void SetValues(List args, PreparedStatement ps) {
		if (ps != null && args != null) {
			for (int i = 0; i < args.size(); i++) {
				try {
					ps.setObject(i + 1, args.get(i));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
