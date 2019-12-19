package com.atomikos.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdbctemplate.Member;
import com.jdbctemplate.manager.JdbcTestMng;

@Controller
@RequestMapping("/jdbcTest_double")
public class JdbcTestDouble {
    @Resource JdbcTestMng jdbcTestMngImpl;
    @Resource JdbcTestMng jdbcTestMngImpl_ext;
    
    /** 对象查询测试
    * @return
    */
   @RequestMapping("/getMemberList")
   @ResponseBody
   public List<Member> getMemberList(){
       return jdbcTestMngImpl.getList();
//       return jdbcTestMngImpl_ext.getList();
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
       return jdbcTestMngImpl_ext.insertMember();
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
