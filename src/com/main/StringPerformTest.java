package com.main;

import java.util.Calendar;


/**
 * 总结：StringBuilder 更快一点，但是1000000次的拼接都不会超过一秒，因此用StringBuffer更好。
 */
public class StringPerformTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                StringBufferTest();
            }
        }).start();;
        new Thread(new Runnable() {
            public void run() {
                StringBuilderTest();
            }
        }).start();;
    }
    public static void StringBufferTest(){
        System.out.println("now:"+Calendar.getInstance().getTime());
        long time1=Calendar.getInstance().getTimeInMillis();
        StringBuffer str=new StringBuffer();
        for (int i = 0; i < 1000000; i++) {
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
        }
        long time2=Calendar.getInstance().getTimeInMillis();
        System.out.println("StringBuffer use time:"+(time2-time1)+"ms");
    }
    public static void StringBuilderTest(){
        System.out.println("now:"+Calendar.getInstance().getTime());
        long time1=Calendar.getInstance().getTimeInMillis();
        StringBuilder str=new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
            str.append("我是线程");
        }
        long time2=Calendar.getInstance().getTimeInMillis();
        System.out.println("StringBuilder use time:"+(time2-time1)+"ms");
    }
}
