package com.beetlsql.model;
import java.io.Serializable;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.DateTemplate;
import org.beetl.sql.core.annotatoin.TableTemplate;

import java.sql.Timestamp;

/*
* 
* gen by beetlsql 2017-08-25
*/
@TableTemplate("order by sort desc ")
public class Member   implements Serializable{
	private Integer member_id ;
	private Integer sort ;
	private String operator ;
	private String password ;
	private String username ;
	private Long create_time ;
	private Date date;
	
	
	public Member() {
	}
	
	public Integer getMember_id(){
		return  member_id;
	}
	public void setMember_id(Integer member_id ){
		this.member_id = member_id;
	}
	
	public Integer getSort(){
		return  sort;
	}
	public void setSort(Integer sort ){
		this.sort = sort;
	}
	
	public String getOperator(){
		return  operator;
	}
	public void setOperator(String operator ){
		this.operator = operator;
	}
	
	public String getPassword(){
		return  password;
	}
	public void setPassword(String password ){
		this.password = password;
	}
	
	public String getUsername(){
		return  username;
	}
	public void setUsername(String username ){
		this.username = username;
	}

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	

}

