package cn.carhouse.http_sample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.carhouse.http.ObjectPresenter;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.BeanCallback;
import cn.carhouse.http_sample.http.ObjectCallback;
import cn.carhouse.http_sample.presenter.MainOKPresenter;
import cn.carhouse.http_sample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IObjectCallback {

    private TextView tv;
    private MainOKPresenter okPresenter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        okPresenter = new MainOKPresenter(this);
        tv = findViewById(R.id.tv);

//        OkHttpPresenter.get(url, new BeanCallback<String>() {
//            @Override
//            public void onSucceed(String result) {
//                tv.setText(result);
//            }
//
//            @Override
//            public void onFailed(Throwable e) {
//                Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

        presenter.test(this);

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
