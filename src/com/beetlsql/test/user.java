package com.beetlsql.test;
import java.io.Serializable;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-08-25
*/
public class user   implements Serializable{
	private Integer id ;
	private Integer age ;
	//用户角色
	private Integer roleId ;
	private Integer sort ;
	private String name ;
	//用户名称
	private String userName ;
	private Date create_date ;
	
	public user() {
	}
	
	public Integer getId(){
		return  id;
	}
	public void setId(Integer id ){
		this.id = id;
	}
	
	public Integer getAge(){
		return  age;
	}
	public void setAge(Integer age ){
		this.age = age;
	}
	
	public Integer getRoleId(){
		return  roleId;
	}
	public void setRoleId(Integer roleId ){
		this.roleId = roleId;
	}
	
	public Integer getSort(){
		return  sort;
	}
	public void setSort(Integer sort ){
		this.sort = sort;
	}
	
	public String getName(){
		return  name;
	}
	public void setName(String name ){
		this.name = name;
	}
	
	public String getUserName(){
		return  userName;
	}
	public void setUserName(String userName ){
		this.userName = userName;
	}
	
	public Date getCreate_date(){
		return  create_date;
	}
	public void setCreate_date(Date create_date ){
		this.create_date = create_date;
	}
	
	
	

}

