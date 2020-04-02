package cn.carhouse.http_sample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.BeanCallback;
import cn.carhouse.http_sample.presenter.MainOKPresenter;
import cn.carhouse.http_sample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IObjectCallback {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        initNet1();
    }

    private void initNet1() {
        // 方式一：直接回调
        MainOKPresenter.test(this, new BeanCallback<String>() {
            @Override
            public void onSucceed(String result) {
                tv.setText(result);
            }

            @Override
            public void onFailed(Throwable e) {
                Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initNet2() {
        // 方式二：间接回调
        MainPresenter.test(this, this);
    }

    @Override
    public void onSuccess(String json, Object object, Class clazz) {
        tv.setText(json);
    }

    @Override
    public void onError(Exception e) {
        Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
    }
}
