package com.beetlsql.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beetlsql.manager.BeetlSqlTestMng;
import com.beetlsql.model.Member;


@Controller
@RequestMapping("/beetlSqlTest")
public class BeetlSqlTest {
    @Resource BeetlSqlTestMng jdbcTestMng;
    
    
    
    /**
     * 对象查询测试
     * @return
     */
    @RequestMapping("/getMemberList")
    @ResponseBody
    public List<Member> getMemberList(){
        return jdbcTestMng.getList();
    }
    /**
     * 对象查询测试
     * @return
     */
    @RequestMapping("/getMember")
    @ResponseBody
    public Member getMember(Integer member_id){
        return jdbcTestMng.getMember(member_id);
    }
    /**
     * 对象插入测试
     * @return
     */
    @RequestMapping("/insertMember")
    @ResponseBody
    public Member insertMember(){
        return jdbcTestMng.insertMember();
    }
    /**
     * 对象删除测试
     * @return
     */
    @RequestMapping("/deleteMember")
    @ResponseBody
    public Member deleteMember(Integer member_id){
        return jdbcTestMng.deleteMember(member_id);
    }
    /**
     * 对象更新测试
     * @return
     */
    @RequestMapping("/updateMember")
    @ResponseBody
    public Member updateMember(){
        return jdbcTestMng.updateMember();
    }
}
