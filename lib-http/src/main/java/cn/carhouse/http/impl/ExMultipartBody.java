package cn.carhouse.http.impl;

import android.os.Handler;
import android.os.Looper;


import java.io.IOException;

import cn.carhouse.http.core.ICallback;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

/**
 * 文件上传，进度监听的代理类
 */

public class ExMultipartBody extends RequestBody {
    private RequestBody mRequestBody;
    private ICallback mCallback;
    private int mCurrentLength;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public ExMultipartBody(RequestBody requestBody) {
        mRequestBody = requestBody;
    }

    public void setCallback(ICallback callback) {
        mCallback = callback;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        final long totalLength = contentLength();
        // 用OKIO的代理类
        ForwardingSink forwardingSink = new ForwardingSink(sink) {
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                mCurrentLength += byteCount;
                // 上传进度回调
                if (mCallback != null) {
                    final float progress = mCurrentLength * 1.0f / totalLength;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onProgress(progress, mCurrentLength, totalLength);
                        }
                    });

                }
            }
        };
        BufferedSink buffer = Okio.buffer(forwardingSink);
        mRequestBody.writeTo(buffer);
        buffer.flush();
    }
}
