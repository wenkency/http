package cn.carhouse.http_sample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.carhouse.http.HttpUtils;
import cn.carhouse.http.ObjectPresenter;
import cn.carhouse.http.OkHttpPresenter;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.BeanCallback;
import cn.carhouse.http_sample.http.ObjectCallback;
import cn.carhouse.http_sample.presenter.MainOKPresenter;
import cn.carhouse.http_sample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IObjectCallback {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        // 自动取消要初始化一下
        HttpUtils.init(getApplication());
        // 测试
        MainPresenter presenter = new MainPresenter(this);
        MainOKPresenter okPresenter = new MainOKPresenter(this);
        // 间接回调
        presenter.test(this);
        // 直接回调
        okPresenter.test(new BeanCallback<String>() {
            @Override
            public void onSucceed(String result) {
                tv.setText(result);
            }

            @Override
            public void onFailed(Throwable e) {
                Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // 如果通过单例直接调用请求，不会自动取消
        // ObjectPresenter.getInstance().get();
        // OkHttpPresenter.getInstance().get();
    }

    @Override
    public void onSuccess(Object object, Class clazz) {
        tv.setText(object.toString());
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
    }
}
