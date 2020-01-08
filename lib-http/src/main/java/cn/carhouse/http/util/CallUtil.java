package cn.carhouse.http.util;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 请求的Util，在生命周期调用，这样请求就可以自动取消
 */
public class CallUtil {
    private static CallUtil instance;


    public static CallUtil getInstance() {
        if (instance == null) {
            synchronized (CallUtil.class) {
                if (instance == null) {
                    instance = new CallUtil();
                }
            }
        }
        return instance;
    }

    // 所有请求的Call
    private List<Call> mCalls = new ArrayList<>();
    // 要取消的网络
    private List<Call> mCancelCalls = new ArrayList<>(1);

    public void add(Call call) {
        mCalls.add(call);
    }

    public void remove(Call call) {
        mCalls.remove(call);
    }

    public void clear() {
        mCalls.clear();
    }


    /**
     * 取消网络请求，在onDesdroid
     *
     * @param tag
     */
    public void cancel(Object tag) {
        try {
            if (tag != null) {
                if (mCalls == null || mCalls.size() <= 0) {
                    return;
                }
                mCancelCalls.clear();
                for (Call call : mCalls) {
                    // 已经取消了的
                    if (call.isCanceled()) {
                        mCancelCalls.add(call);
                        continue;
                    }
                    if (tag.equals(call.request().tag())) {
                        call.cancel();
                        mCancelCalls.add(call);
                    }
                }
                // 从集合中移除
                if (mCancelCalls.size() > 0) {
                    mCalls.removeAll(mCancelCalls);
                }
                mCancelCalls.clear();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
