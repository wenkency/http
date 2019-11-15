package cn.carhouse.http.callback;

import java.io.File;

public abstract class FileCallback extends DefaultCallback {
    @Override
    public abstract void onProgress(float progress, float current, float total);

    @Override
    public abstract void onSucceed(File file);
}
