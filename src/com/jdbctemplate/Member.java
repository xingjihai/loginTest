package com.jdbctemplate;

import java.io.Serializable;

import com.jdbctemplate.util.JdbcUtil.NotDbField;
import com.jdbctemplate.util.JdbcUtil.PrimaryKey;
import com.jdbctemplate.util.JdbcUtil.Table;

@Table(table="member",key="member_id")
public class Member implements Serializable {
    @PrimaryKey
    private Integer member_id;
    private String username;
    private String password;
    private String operator;
    private Integer sort;
    private Long create_time ;
    //not field
    @NotDbField
    public String nofield;
    
    public Member() {
        // TODO Auto-generated constructor stub
    }
    public Member(String username,String password) {
        this.username=username;
        this.password=password;
    }
    
    public Integer getMember_id() {
        return member_id;
    }
    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNofield() {
        return nofield;
    }
    public void setNofield(String nofield) {
        this.nofield = nofield;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public Long getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
    
}
