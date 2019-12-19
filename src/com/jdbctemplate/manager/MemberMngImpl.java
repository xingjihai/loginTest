package com.jdbctemplate.manager;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdbctemplate.Member;
import com.jdbctemplate.util.BaseSupport;

@Service("MemberMngImpl")
public class MemberMngImpl  extends BaseSupport<Member> implements MemberMng{

    public MemberMngImpl() {
        System.out.println("init");
    }
    
    
    public void add(Member member) {
        member.setOperator("MemberMngImpl");
        this.daoSupport.insert(member);
    }


    public Member findById(Integer id) {
//        int random=(int) (Math.random()*10);
//        System.out.println(random);
//        if(random>5){
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//            }
//        }
//        System.out.println(random+"线程访问" + this.hashCode());
        
        
        String sql="select * from member where member_id =? ";
        return this.daoSupport.queryForObject(sql, Member.class, id);
    }
    
    @Transactional
    public void transactionTest() {
        Member member1=new Member("小明666","密码666");
        Member member2=new Member("小明777","密码777");
        try {                     //如果用try catch进行异常的捕获，则不会将异常抛出，事务aop也就监听不到异常，因此不会回滚。
            add(member1);
            if(1==1)
                throw new RuntimeException("抛出异常");
        } catch (Exception e) {
            e.printStackTrace();           
        }
        add(member2);
    }
    
    
    
    /**
     * 测试hashcode能不能检测出对象的唯一性（结果证明：new出来的10个对象hashcode都不一样）
     * 关于hashcode:
     * 1、两个对象相同（equals），则hashcode一定相同
     * 2、两个对象的hashCode相同（equals），并不一定表示两个对象就相同，只能说他们放在同一篮子里
     * 3、hashcode是用来查找的
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Member member=new Member();
            System.out.println(member.hashCode());
        }
        Member member1=new Member();
        Member member2=new Member();
        System.out.println(member1.equals(member2));
    }


    public List<Member> getAllMember() {
        String sql="select * from member ";
        List<Member> list=this.daoSupport.queryForList(sql, Member.class);
        return list;
    }


    public Member update(Member member) {
        this.daoSupport.update(member,"member_id ="+member.getMember_id());
        return  this.findById(member.getMember_id());
    }


    public void delete(Member member) {
        String sql="delete from member where member_id =? ";
        this.daoSupport.execute(sql, member.getMember_id());
    }
}
