package com.jdbctemplate.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdbctemplate.Member;
import com.jdbctemplate.util.BaseSupport;
import com.jdbctemplate.util.Page;

@Service
@Transactional
public class JdbcTestMngImpl extends BaseSupport<Member> implements JdbcTestMng{

    @SuppressWarnings({ "unchecked", "unused" })
    public List<Member> getList() {
        
        /** ****************** 对象查询 start **********************/
        
        Member condition_member=new Member();
//        condition_member.setMember_id(1);
//        condition_member.setOperator("111");
        
        /*  以对象为查询条件 查询List */
        
        List<Member> list1=this.daoSupport.queryListByClass(condition_member,"order by sort desc ",Member.class);
        
        /* 以对象为查询条件 查询page
         * 应用情况：对等查询
         */
        Page page=this.daoSupport.queryForPageByClass( condition_member,1,10,"order by sort desc",Member.class);
        List<Member> list2=(List<Member>) page.getResult();
        
        
        /*
         *   查询List
         *应用情况：非对等查询
         */
        String sql="select * from member where sort >= ? ";
        List<Member> list3=this.daoSupport.queryForList(sql, Member.class, 1);
        /*
         *   查询List
         *应用情况：非对等查询
         */
        Page page2=this.daoSupport.queryForPage(sql, 2, 3, Member.class, 0); 
        System.out.println( page2.getCurrentPageNo() );
        List<Member> list4= (List<Member>) page2.getResult();
        
        
        /******************** 对象查询 end **********************/
        
        
        /*
         *查询List<Map>
         *应用情况：非对等查询
         */
//        List<Map<String, Object>> list5=this.daoSupport.queryForList(sql, 1);
//        for (Map<String, Object> map : list5) {
//            System.out.println(map.get("username"));
//        }
        
        return list4;
    }
    

    public Member getMember(Integer id) {
        /**
         * 根据id查询
         */
        return this.daoSupport.queryObjectByKey(id, Member.class);
    }
    
    public Member insertMember(){
        Member member=new Member();
        member.setUsername("小明");
        member.setPassword("密码");
        return this.daoSupport.insert(member);
    }


    public Member updateMember() {
        Member member=new Member();       /** 注意：这种方法要确保Member对象中的其他无关字段get出来都为空！！！否则会更新错  ，或者根据id先get出原对象，然后再赋值到对象中。  */
        member.setMember_id(1);
        member.setUsername("小明");
        member.setPassword("密码");
        this.daoSupport.update(member, "member_id ="+member.getMember_id());
        
        return this.daoSupport.queryObjectByKey(member.getMember_id().toString() , Member.class);
    }


    public Member deleteMember(Integer id) {
        this.daoSupport.delete(id.toString(), Member.class);
        return null;
    }
}