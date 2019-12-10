package cn.carhouse.http_sample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.carhouse.http.ObjectPresenter;
import cn.carhouse.http.core.IObjectCallback;
import cn.carhouse.http_sample.http.ObjectCallback;

public class MainActivity extends AppCompatActivity implements IObjectCallback {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        String url = "https://www.baidu.com/";
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

        ObjectPresenter.get(url, String.class, new ObjectCallback(this));
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
