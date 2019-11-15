package cn.carhouse.http.callback;


import java.io.File;

import cn.carhouse.http.core.ICallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.core.RequestType;


public class DefaultCallback implements ICallback {
    @Override
    public void onBefore(RequestParams params, RequestType type) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onSucceed(RequestParams params, String result, boolean isSuccessful, int code) {

    }

    @Override
    public void onAfter() {

    }

    @Override
    public void onProgress(float progress, float current, float total) {

    }

    @Override
    public void onSucceed(File file) {

    }
}
