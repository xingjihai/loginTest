package com.jdbctemplate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdbctemplate.Member;
import com.jdbctemplate.manager.JdbcTestMng;
import com.jdbctemplate.manager.MemberMng;

@Controller
@RequestMapping("/jdbcTest")
public class JdbcTest {
    @Resource MemberMng MemberMngImpl;
    @Resource MemberMng MemberMngImpl2;
    @Resource JdbcTestMng jdbcTestMngImpl;
    
    @RequestMapping("/addMember")
    @ResponseBody
    public String addMember(){
        Member member=new Member();
        member.setMember_id(22);
        member.setPassword("123456");
        member.setUsername("小明");
        member.setNofield("nofield");
        MemberMngImpl.add(member);
        Member member2=new Member();
        member2.setMember_id(22);
        member2.setPassword("123456");
        member2.setUsername("小明2");
        member2.setNofield("nofield");
        MemberMngImpl2.add(member2);
        return "success";
    }
    
    /**
     * 测试事务回滚
     * @return
     */
    @RequestMapping("/transactionTest")
    @ResponseBody
    public String transactionTest(){
        MemberMngImpl.transactionTest();
        return "success";
    }
    
    
    /**
     * 对象查询测试
     * @return
     */
    @RequestMapping("/getMemberList")
    @ResponseBody
    public List<Member> getMemberList(){
        return jdbcTestMngImpl.getList();
    }
    /**
     * 对象查询测试
     * @return
     */
    @RequestMapping("/getMember")
    @ResponseBody
    public Member getMember(Integer member_id){
        return jdbcTestMngImpl.getMember(member_id);
    }
    /**
     * 对象插入测试
     * @return
     */
    @RequestMapping("/insertMember")
    @ResponseBody
    public Member insertMember(){
        return jdbcTestMngImpl.insertMember();
    }
    /**
     * 对象删除测试
     * @return
     */
    @RequestMapping("/deleteMember")
    @ResponseBody
    public Member deleteMember(Integer member_id){
        return jdbcTestMngImpl.deleteMember(member_id);
    }
    /**
     * 对象更新测试
     * @return
     */
    @RequestMapping("/updateMember")
    @ResponseBody
    public Member updateMember(){
        return jdbcTestMngImpl.updateMember();
    }
}
