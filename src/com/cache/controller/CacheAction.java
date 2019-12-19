package com.cache.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cache.service.MemberMngProxy;
import com.cache.util.EhCacheUtil;
import com.jdbctemplate.Member;
import com.jdbctemplate.manager.MemberMng;


/**
 *  缓存代理类测试
 */
@Controller
public class CacheAction {
    @Resource MemberMng MemberMngImpl;
    
    
    @RequestMapping("/ehcacheTest")
    @ResponseBody
    public void ehcacheTest(){
        Member member1=new Member();
        member1.setUsername("member1");
        Member member2=new Member();
        member2.setUsername("member2");
        List<Member> list=new ArrayList<Member>();
        list.add(member1);
        list.add(member2);
        
        EhCacheUtil cache = new EhCacheUtil("ehcache_test");
        List<Member> cacheList=(List<Member>) cache.get("test");
        System.out.println( new Date() );
        if(cacheList!=null){
            System.out.println("get from cache!!");
        }else{
            cache.put("test",list);
        }
//        cache.put("test",list,20);
        cacheList=(List<Member>) cache.get("test");
        for (Member member : cacheList) {
            System.out.println(member.getUsername());
        }
    }
    
    @RequestMapping("/clearAll")
    @ResponseBody
    public String clearAll(){
        EhCacheUtil.clearAll();
        return "清除全部缓存成功";
    }
    
    
    /**
     * service缓存代理测试
     */
    @RequestMapping("/cacheProxyTest")
    @ResponseBody
    public List<Member> cacheProxyTest(){
        MemberMngProxy MemberMngProxy=new MemberMngProxy(MemberMngImpl);
        List<Member> list=MemberMngProxy.getAllMember();
        return list;
    }
    
    
    public static void main(String[] args) {
        System.out.println( System.getProperty("java.io.tmpdir") );
    }
}
