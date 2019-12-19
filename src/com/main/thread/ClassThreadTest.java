package com.main.thread;


/**
 * 测试同一个类实例被多个线程访问的情况
 *  结果证明：
 *  1、多个线程访问同一个类实例的方法，其他线程阻塞后，不会阻塞。
 *  2、thread_code和count 变量会受多线程影响。 
 *  3、多线程变量要用 threadlocal
 *  4、如果需要同步，需要考虑加锁。
 */
public class ClassThreadTest{
    private static int i=0;
    private String thread_code="";
    private int count=0;
    private ThreadLocal<Integer> count2=new ThreadLocal<>();
    
    public Integer getCount2() {
        Integer c=count2.get();
        if(c==null){
            c=0;
            count2.set(c);
        }
        return c;
    }

    public void setCount2(Integer count) {
        count2.set(count);
    }

    public void threadTest(){
        try {
            thread_code=""+Thread.currentThread().getName();
            System.out.println("线程"+Thread.currentThread().getName()+"执行开始,thread_code="+thread_code);
            System.out.println("线程"+Thread.currentThread().getName()+"执行开始,count="+count);
            System.out.println("线程"+Thread.currentThread().getName()+"执行开始,Count2="+getCount2());
            i++;
            if(i<=2){
                Thread.sleep(1000);
            }
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕,thread_code="+thread_code);
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕,count="+count);
            System.out.println("线程"+Thread.currentThread().getName()+"执行完毕,Count2="+getCount2());
            count++;
            setCount2( getCount2()+1) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        final ClassThreadTest classThreadTest=new ClassThreadTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {        //初始化后 Runnable作为Thread的属性
                public void run() {
                    classThreadTest.threadTest();
                }
            }).start();
        }
    }

    public String getThread_code() {
        return thread_code;
    }

    public void setThread_code(String thread_code) {
        this.thread_code = thread_code;
    }
    
}
