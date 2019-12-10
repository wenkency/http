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


implementation 'com.github.wenkency:http:1.1.0'

```

### 使用方式
```
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
```

### 运行结果

<img src="screenshot/image.jpg" width="360px"/>