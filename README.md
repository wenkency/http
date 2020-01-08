# http
okhttp网络请求封装库，支持get post put delete请求，支持文件下载和上传。

### 引入

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


implementation 'com.github.wenkency:http:1.2.0'

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
        // 自动取消要初始化一下
        HttpUtils.init(getApplication());
        // 测试
        MainPresenter  presenter = new MainPresenter(this);
        MainOKPresenter   okPresenter = new MainOKPresenter(this);
        // 间接回调--可同时请求多个网络
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
    }
    // 间接成功回调
    @Override
    public void onSuccess(Object object, Class clazz) {
        tv.setText(object.toString());
    }
    // 间接失败回调
    @Override
    public void onError(Exception e) {
        Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
    }

```

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>