package cn.carhouse.http_sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import cn.carhouse.http.OkHttpPresenter;
import cn.carhouse.http.callback.StringCallback;
import cn.carhouse.http.core.RequestParams;
import cn.carhouse.http_sample.http.BeanCallback;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        String url = "https://www.baidu.com/";
        OkHttpPresenter.get(url, new BeanCallback<String>() {
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
}
