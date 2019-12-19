package com.cache.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cache.service.MemberMngProxy2;
import com.jdbctemplate.Member;
import com.jdbctemplate.manager.MemberMng;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;


/**
 *  annotation缓存测试
 */
@Controller
public class CacheAction2 {
    @Resource MemberMng MemberMngImpl;
    @Resource MemberMng MemberMngProxy2;
    
    
    
    /**
     * service缓存代理测试
     */
    @RequestMapping("/cacheProxy2Test")
    @ResponseBody
    public Member cacheProxy2Test(Integer member_id){
        CacheManager manager = CacheManager.getInstance();
        Cache cache=manager.getCache("member");
        Member member=MemberMngProxy2.findById(member_id);
        return member;
    }
    
    /**
     * service缓存代理测试
     */
    @RequestMapping("/cacheProxy2Test2")
    @ResponseBody
    public Member cacheProxy2Test2(String name){
        Member member=new Member();
        member.setMember_id(3);
        member.setUsername(name);
        Member return_member=MemberMngProxy2.update(member);
        return return_member;
    }
    /**
     * service缓存代理测试
     */
    @RequestMapping("/cacheProxy2Test3")
    @ResponseBody
    public List<Member> cacheProxy2Test3(){
        List<Member> list=MemberMngProxy2.getAllMember();
        return list;
    }
    
    /**
     * service缓存代理测试
     */
    @RequestMapping("/cacheProxy2Test4")
    @ResponseBody
    public void cacheProxy2Test4(){
        Member member=new Member();
        member.setMember_id(1);
        MemberMngProxy2.delete(member);
    }
}
