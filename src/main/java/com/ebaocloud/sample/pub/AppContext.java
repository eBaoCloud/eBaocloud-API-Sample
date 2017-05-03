package com.ebaocloud.sample.pub;

/**
 * Created by Guo Rui on 5/3/17.
 */
public class AppContext {
    private static final ThreadLocal<AppContext> threadLocal = new ThreadLocal<>();

    private AppContext() {
    }

    private String token;

    public static AppContext getInstance() {
        if (threadLocal.get() == null) {
            synchronized (threadLocal) {
                if (threadLocal.get() == null) {
                    threadLocal.set(new AppContext());
                }
                return threadLocal.get();
            }
        }
        return threadLocal.get();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
