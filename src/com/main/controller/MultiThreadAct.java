package com.main.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdbctemplate.Member;
import com.jdbctemplate.manager.MemberMng;


/**
 * 多线程并发测试
 * 测试结果：
 * 1、多次请求访问的是同一个MultiThreadAct对象。
 * 2、线程间不会受到其他线程阻塞的影响
 * 3、多个线程访问的类的属性是同一个类的属性（即同一个属性），因此会相互影响 ，因此如果要保证数据的正确性 就要进行同步处理（但同步处理又会造成阻塞）
 */
@Controller
@RequestMapping("/multiThread")
public class MultiThreadAct {
    @Resource MemberMng MemberMngImpl;
    public static int i=0;
    private String thread_code="";
    ThreadLocal<String> thread_code2=new ThreadLocal<String>();  //多线程变量
    
    /**
     * 并发测试，测试springmvc中的线程是否会相互影响（当一个线程阻塞时，其他线程是等待还是继续执行？）
     * 测试结果：
     * 
        3
        3线程访问6
        0
        0线程访问7
        6
        4
        4线程访问9
        4
        4线程访问10
        6线程访问10    --6访问到了脏数据（其实应该打印8）
     */
    @RequestMapping("/parallelTest")
    @ResponseBody
    public void parallelTest() throws InterruptedException{
        if(i!=0){
            i++;        //实验证明： 当对i(静态变量) 操作时一定要进行同步处理，否则会读到脏数据
            int random=(int) (Math.random()*10);
            System.out.println(random);
            if(random>5){
                Thread.sleep(10000);
            }
            System.out.println(random+"线程访问"+i);
        }else{
            i++;
        }
    }
    /**
     * 并发测试，测试springmvc中的线程是否会相互影响（当一个线程阻塞时，其他线程是等待还是继续执行？）
     * 测试结果：
     * 证明：springmvc的线程不会等待其他线程
     */
    @RequestMapping("/parallelTest2")
    @ResponseBody
    public void parallelTest2() throws InterruptedException{
        int random=(int) (Math.random()*10);
        System.out.println(random);
        if(random>5){
            Thread.sleep(10000);
        }
        System.out.println(random+"线程访问"+this.hashCode());
    }
    
    /**
     * 并发测试，测试 <b>spring bean </b> 中的线程是否会相互影响 
     * 测试结果：
     * 证明：<b>spring bean </b> 不会互相影响
     */
    @RequestMapping("/parallelTest3")
    @ResponseBody
    public void parallelTest3() throws InterruptedException{
        
        Member member=MemberMngImpl.findById(1);
        System.out.println(member);
        
    }
    
    /**
     * 并发测试，测试 各个线程 在 访问 MultiThreadAct的属性时(非静态)  会不会相互影响。
     * 测试结果： 
     *  会相互影响
     */
    @RequestMapping("/parallelTest4")
    @ResponseBody
    public void parallelTest4() throws InterruptedException{
        
        thread_code=""+Thread.currentThread().hashCode();
        System.out.println("线程"+Thread.currentThread().hashCode()+"执行开始,thread_code="+thread_code);
        int random=(int) (Math.random()*10);
        System.out.println(random);
        if(random>=5){
            Thread.sleep(10000);
        }
        System.out.println("线程"+Thread.currentThread().hashCode()+"执行完毕,thread_code="+thread_code);
    }
    
    /**
     * 并发测试，对比4的结果，测试ThreadLocal 在多线程中的作用。
     * 测试结果： 
     * ThreadLocal变量再多线程中 不会相互影响
     */
    @RequestMapping("/parallelTest5")
    @ResponseBody
    public void parallelTest5() throws InterruptedException{
        thread_code2.set(""+Thread.currentThread().hashCode());
        System.out.println("线程"+Thread.currentThread().hashCode()+"执行开始,thread_code2="+thread_code2.get());
        int random=(int) (Math.random()*10);
        System.out.println(random);
        if(random>=5){
            Thread.sleep(10000);
        }
        System.out.println("线程"+Thread.currentThread().hashCode()+"执行完毕,thread_code2="+thread_code2.get());
    }
    
    
    
    
    
    public String getThread_code() {
        return thread_code;
    }
    public void setThread_code(String thread_code) {
        this.thread_code = thread_code;
    }
    
    
}
