package com.practic.waimai.common;

public class ThreadContext {
    private static ThreadLocal t = new ThreadLocal();

    public static void setCurrentId(Long id) {
        t.set(id);
    }

    public static Long getCurrentId() {
       return (Long) t.get();
    }
}
