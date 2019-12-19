package com.beetlsql.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SQLReady;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beetlsql.model.Member;


@Service
@Transactional
public class BeetlSqlTestMngImpl  implements BeetlSqlTestMng{
    @Resource  SQLManager sqlManager ;
    
    
    @SuppressWarnings({ "unchecked", "unused" })
    public List<Member> getList() {
        
        /** ****************** 对象查询 start **********************/
        
        Member condition_member=new Member();
        condition_member.setOperator("111");
        
        /* 1、  以对象为查询条件 查询List */
        
//        List<Member> list1=this.daoSupport.queryListByClass(condition_member,"order by sort desc ",Member.class);
        
        List<Member> list1=sqlManager.template(condition_member);  //条件查询需要在类上注解，或者写md
        
        /* 2、以对象为查询条件 查询page
         * 应用情况：对等查询
         */
//        Page page=this.daoSupport.queryForPageByClass( condition_member,1,10,"order by sort desc",Member.class);
//        List<Member> list2=(List<Member>) page.getResult();
        
        List<Member> list2=sqlManager.template(condition_member,1, 10);
        
        /*
         * 3、查询List
         *应用情况：非对等查询 >,< 号
         */
//        String sql="select * from member where sort >= ? ";
//        List<Member> list3=this.daoSupport.queryForList(sql, Member.class, 1);
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("end_time", 1503661335532L);
        List<Member> list3=sqlManager.select("member.select", Member.class,paramMap);  /**order by直接写在md中*/
        
        
        /*
         *  4、 查询List
         *应用情况：非对等查询
         */
//        Page page2=this.daoSupport.queryForPage(sql, 2, 3, Member.class, 0); 
//        System.out.println( page2.getCurrentPageNo() );
//        List<Member> list4= (List<Member>) page2.getResult();
      //从第一页开始查询,无参数
        PageQuery<Member> query = new PageQuery<Member>();
        query.setOrderBy("sort desc,member_id asc");               /**注意：分页时md中不能有order by*/
        query.setPageNumber(1);
        query.setPageSize(5);
//        query.setParas(paramMap);
        query=sqlManager.pageQuery("member.selectPage", Member.class,query);
        System.out.println(query.getTotalPage());
        System.out.println(query.getTotalRow());
        System.out.println(query.getPageNumber());
        List<Member> list4=query.getList();
        
        
        /*
         * 5、直接执行JDBC sql语句
         */
        String  jdbcSql = "  select * from member order by member_id";
        PageQuery query2 = new PageQuery(1,20);
        query2 = sqlManager.execute(new SQLReady(jdbcSql), Member.class, query2);
        List<Member> list5 =query2.getList();
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
//        return this.daoSupport.queryObjectByKey(id, Member.class);
        return sqlManager.unique(Member.class, id);
    }
    
    public Member insertMember(){
        Member member=new Member();
        member.setUsername("小明");
        member.setPassword("密码");
        member.setCreate_time(System.currentTimeMillis());
        member.setDate(new Date());
        sqlManager.insert(member);
        return null;
    }


    public Member updateMember() {
        Member member=new Member();  /** 注意：这种方法要确保Member对象中的其他无关字段get出来都为空！！！否则会更新错  ，或者根据id先get出原对象，然后再赋值到对象中。  */
        member.setMember_id(6);
        member.setUsername("beetlsql");
        member.setPassword("密码");
        sqlManager.updateTemplateById(member);
        
        
        
//        String sql=" update member set operator=#operator# where username=#username#  ;" ;
//        Map<String,Object> paramMap=new HashMap<>();
//        paramMap.put("operator", "111");
//        paramMap.put("username", "小明");
//        sqlManager.executeUpdate(sql, paramMap);
        return null;
    }


    public Member deleteMember(Integer id) {
        sqlManager.deleteById(Member.class, id);
        return null;
    }
}