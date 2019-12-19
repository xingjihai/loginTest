package com.cache.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jdbctemplate.Member;
import com.jdbctemplate.manager.MemberMng;


/**
 * spring 注解缓存示例
 */
@Service("MemberMngProxy2")
public class MemberMngProxy2 implements MemberMng{
    @Resource
    private MemberMng MemberMngImpl ;
    

    public void add(Member member) {
        MemberMngImpl.add(member);
    }

    /**
     * @Cacheable 在缓存有效期内返回缓存，不再执行方法中的代码段。 满足condition条件才会缓存和获取缓存
     */
//  可以设置多个存储源  @Cacheable({"member","member2"})
    @Cacheable(value="member",key="#id",condition = "#id <= 2")
    public Member findById(Integer id) {
        try {
            Thread.sleep(1000);
            System.out.println(new Date()+"延迟");
        } catch (InterruptedException e) {
        }
        return MemberMngImpl.findById(id);
    }
    
    /**
     * @CachePut 执行方法中的代码段！！！  将结果缓存，但不获取缓存
     */
    @CachePut(value = "member", key = "#member.getMember_id()")
    public Member update(Member member) {
        try {
            Thread.sleep(1000);
            System.out.println(new Date()+"延迟");
        } catch (InterruptedException e) {
        }
        return MemberMngImpl.update(member);
    }
    
    @Cacheable(value="member")
    public List<Member> getAllMember() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return MemberMngImpl.getAllMember();
    }
    
    /**
     * @CacheEvict 用来删除失效或无用的缓存数据。 <br/>
     * 注意key要与findById一致。
     */
    @CacheEvict(value = "member", key = "#member.getMember_id()")
    public void delete(Member member) {
        MemberMngImpl.delete(member);
    }

    public void transactionTest() {
        MemberMngImpl.transactionTest();
    }



   
}
