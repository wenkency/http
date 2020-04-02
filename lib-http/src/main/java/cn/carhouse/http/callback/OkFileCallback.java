package cn.carhouse.http.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.carhouse.http.core.ICallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http.util.NetFileUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 文件下载的回调
 */

public class OkFileCallback implements Callback {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String mFileName;
    private ICallback mCallback;
    private Context mContext;
    private String mFileDir;

    public OkFileCallback(RequestParams params) {
        this.mContext = params.getContext();
        this.mCallback = params.getCallback();
        this.mFileName = params.getFileName();
        this.mFileDir = params.getFileDir();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (mCallback != null) {
            mCallback.onError(e);
            mCallback.onAfter();
        }
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[1024 * 1024];
        int len = -1;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;
            NetFileUtil.init(mContext);
            File dir =  NetFileUtil.createCacheDir(mFileDir);
            final File file = new File(dir, mFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallback.onProgress(finalSum * 100f / total, finalSum, total);
                    }
                });
            }
            fos.flush();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onSucceed(file);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(is);
            close(fos);
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onAfter();
            }
        });
    }


    private void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
        }
    }
}
