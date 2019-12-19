package com.cache.service;

import java.util.List;

import com.cache.util.AbstractCacheProxy;
import com.jdbctemplate.Member;
import com.jdbctemplate.manager.MemberMng;

/**
 * 缓存代理示例
 */
public class MemberMngProxy extends AbstractCacheProxy implements MemberMng{
    private MemberMng memberMng ;
    public static final String CACHE_KEY = "member";
    
    /**
     * 在初始化时 确定接口memberMng的实现方式
     */
    public MemberMngProxy(MemberMng memberMng) {
        super(CACHE_KEY);
        this.memberMng=memberMng;
    }
    
    public void add(Member member) {
        memberMng.add(member);
    }

    public Member findById(Integer id) {
        Member member=memberMng.findById(id);
        return member;
    }

    public void transactionTest() {
        memberMng.transactionTest();
    }

    @SuppressWarnings("unchecked")
    public List<Member> getAllMember() {
        String key="getAllMember";
        List<Member> list=(List<Member>) cache.get(key);
        if(list==null){
            list=memberMng.getAllMember();
            this.cache.put(key, list);
        }else{
            logger.error("getAllMember from cache !!!");
        }
        return list;
    }

    public Member update(Member member) {
        return memberMng.update(member);
    }

    public void delete(Member member) {
        // TODO Auto-generated method stub
        
    }
}
