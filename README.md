# http
okhttp3网络请求封装库，支持get post put delete 表单(form)请求，支持文件下载和上传。

### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


implementation 'com.github.wenkency:http:1.3.0'

```

### 使用方式
```
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

```

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>