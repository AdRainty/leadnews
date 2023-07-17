package com.heima.utils.thread;

import com.heima.model.wemedia.pojos.WmUser;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/17 21:09
 */
public class WmThreadLocalUtil {

    private WmThreadLocalUtil() {}

    private static final ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(WmUser wmUser) {
        WM_USER_THREAD_LOCAL.set(wmUser);
    }

    public static WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    public static void clear() {
        WM_USER_THREAD_LOCAL.remove();
    }

}
