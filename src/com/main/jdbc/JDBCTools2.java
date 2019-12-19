package com.main.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.org.glassfish.external.statistics.annotations.Reset;

public class JDBCTools2 {
	//sqlserver
	//	public static String URL = "jdbc:sqlserver://localhost:1433;databasename=sf148?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
	//	public static String DRIVERCLASSNAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//mysql
	private  String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
	private  String driverClassName = "com.mysql.jdbc.Driver";
    private  String user = "root";
    private  String password = "admin";
    private Connection connection;
    public JDBCTools2() {
    	try {
    		Class.forName(driverClassName);
    		connection= DriverManager.getConnection(url, user, password);
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public  static Integer count=0;
    public void save(String sql){
    	PreparedStatement stmt = null;
    	try {
			stmt=connection.prepareStatement(sql);
			int i=stmt.executeUpdate();
			
			System.out.println( " stmt.executeUpdate() return : "+i );
			
		} catch (SQLException e) {
			System.out.println("[fail sql]"+sql);
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public void update(String sql){
    	PreparedStatement stmt = null;
    	Connection connection=null;
    	try {
    		Class.forName(driverClassName);
    		connection= DriverManager.getConnection(url, user, password);
			stmt=connection.prepareStatement(sql);
			int i=stmt.executeUpdate();
			
			System.out.println( " stmt.executeUpdate() return : "+i );
			
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("[fail sql]"+sql);
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public ResultSet query(String sql){
    	PreparedStatement stmt = null;
    	try {
			stmt=connection.prepareStatement(sql);
			ResultSet reset =stmt.executeQuery();
			return reset;
		} catch (SQLException e) {
			System.out.println("[fail sql]"+sql);
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return null;
    }
    
    /**
     * 开启100个线程后测试 还是没有同步？why？
Thread-61 count : 91
Thread-19 count : 91
原因：实例化了不同的对象 因此 synchronized声明无效
     */
    public  void count(){
    	synchronized(this){
    		count++;
    		System.out.println(Thread.currentThread().getName()+ " count : "+ count);
    	}
    }
	
    public static void main(String[] args) {
//		try {
//			Class.forName(DRIVERCLASSNAME); //加载类（new一个对象分了两步走：1、加载类 2、实例化对象，而getInstance必须先加载完类）
//			Connection connection= DriverManager.getConnection(URL, USER, PASSWORD);
//			String sql="select column_name, column_comment from information_schema.columns  where table_schema='questionnaire' and table_name='question'";
//			PreparedStatement stmt=connection.prepareStatement(sql);
//			
//			ResultSet set= stmt.executeQuery();
//			while (set.next()) {
//				System.out.println(set.getString(1)+":"+set.getNString(1) +" =========>"+ set.getString(2));
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
    
    
}
