package com.jdbctemplate.manager;

import java.util.List;

import com.jdbctemplate.Member;

public interface MemberMng {
    public void add(Member member);
    
    public Member findById(Integer id);
    
    public Member update(Member member);
    
    public List<Member> getAllMember();
    
    public void delete(Member member);
    
    /**
     * 事务测试
     */
    public void transactionTest();
}
