package com.youdy.test.pattern.singleton;

/**
 * Double Check Singleton
 * synchronized(XXX.class)会阻塞后面返回相同class的synchronized(XXX.class)代码
 * Created By shen on 2017-5-8 22:52
 */
public class DCSingleton {

    private static volatile DCSingleton instance = null;

    private DCSingleton(){};

    public static synchronized DCSingleton getInstance() {
        if (instance == null) {
            synchronized (DCSingleton.class) {
                if (instance == null) {
                    instance = new DCSingleton();
                }
            }
        }
        return new DCSingleton();
    };

}
