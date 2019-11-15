package cn.carhouse.http.util;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 请求的Util，在生命周期调用，这样请求就可以自动取消
 */
public class CallUtil {
    private static CallUtil instance;

    private Object mTag;

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
     * 设置TAG，要在请求页面设置
     *
     * @param tag
     */
    public void setTag(Object tag) {
        // onCreate 和 onResume 设置
        this.mTag = tag;
    }

    public Object getTag() {
        // onPause
        return mTag;
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
                List<Call> calls = new ArrayList<>(1);
                for (Call call : mCalls) {
                    // 已经取消了的
                    if (call.isCanceled()) {
                        calls.add(call);
                        continue;
                    }
                    if (tag.equals(call.request().tag())) {
                        call.cancel();
                        calls.add(call);
                    }
                }
                // 从集合中移除
                if (calls.size() > 0) {
                    mCalls.removeAll(calls);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
