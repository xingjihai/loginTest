package com.main.thread;

import java.util.Calendar;

/**
 * 总结：如果在工具类中修改string 时，一定要用stringbuffer
 */
public class StringTest {
    StringBuffer classstr=new StringBuffer();
//    StringBuilder classstr=new StringBuilder();
    
    
    public static void main(String[] args) {
        final StringTest test=new StringTest();
        for (int i = 0; i < 100; i++) {
            Thread t=new Thread(new Runnable() {
                public void run() {
                    test.methodTest1();
                }
            });
            t.start();
        }
    }
    
    /**
     * 方法中new出来 StringBuffer和StringBuilder都一样不会受到影响。
     */
    public void methodTest1(){
//        StringBuffer str=new StringBuffer();
        StringBuilder str=new StringBuilder();
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        str.append("我是线程"+Thread.currentThread().getName());
        System.out.println(str);
    }
    
    /**
     * 做为类变量
     *  StringBuffer 是线性安全的（能保证其他线程不会得到脏数据,但是如果要保证按线程的顺序执行仍然需要加同步锁）
     *  StringBuilder 是非线性安全的 --会得到脏数据（即数据已经被改到，而它还不知道。）
     */
    public void methodTest2(){
//       
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        classstr.append(Thread.currentThread().getName());
        System.out.println(classstr);
    }
}
