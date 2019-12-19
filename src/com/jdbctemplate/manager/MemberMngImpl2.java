package com.jdbctemplate.manager;


import java.util.List;

import org.springframework.stereotype.Service;

import com.jdbctemplate.Member;
import com.jdbctemplate.util.BaseSupport;

@Service("MemberMngImpl2")
public class MemberMngImpl2  extends BaseSupport<Member> implements MemberMng{

    public void add(Member member) {
        member.setOperator("MemberMngImpl2");
        this.daoSupport.insert( member);
    }

    public Member findById(Integer id) {
        String sql="select * from member where member_id =? ";
        return this.daoSupport.queryForObject(sql, Member.class, id);
    }
    
    
    public void transactionTest() {
        // TODO Auto-generated method stub
        
    }

    public List<Member> getAllMember() {
        // TODO Auto-generated method stub
        return null;
    }

    public Member update(Member member) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(Member member) {
        // TODO Auto-generated method stub
        
    }

    
}
