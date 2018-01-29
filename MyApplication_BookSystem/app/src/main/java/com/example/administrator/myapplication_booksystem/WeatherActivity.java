package com.example.administrator.myapplication_booksystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.entity.CityInfo;
import com.example.administrator.entity.FutureInfo;
import com.example.administrator.entity.Mess;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity extends Activity {

    private EditText mycity;
    //OKHttp客户端对象
    private OkHttpClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_weather);
        client=new OkHttpClient();
        //mycity = findViewById(R.id.city);
    }
    //发送GET请求实现登录操作
    public void test_1(View view){
        //获取城市
        //final String city = mycity.getText().toString();
        //构建一个请求对象
        String url="http://apicloud.mob.com/v1/weather/query?key=appkey&city=北京";
        Request request=new Request.Builder().url(url).build();
        //构建一个Call对象
        Call call=client.newCall(request);
        //异步执行请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("call error","登录出错,"+e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"请连接网络",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //通过response得到服务器响应内容
                final String str=response.body().string();
                Log.i("网络查询天气weather",str);

                //将字符串转换为json数据
                final Mess mess = new Gson().fromJson(str,new TypeToken<Mess>(){}.getType());//所有数据
                CityInfo cityInfo = mess.getResult().get(0);//result --  城市天气所有信息
                Log.i("CityInfo",cityInfo.getCity());//获取的城市
                //获取一周天气
                List<FutureInfo> FutureList = cityInfo.getFuture();
                //遍历一周天气
                for (FutureInfo futureinfo:FutureList) {
                    Log.i("futureinfo",futureinfo.getDate()+":"+futureinfo.getTemperature());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,mess.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                });

                Log.i("CityInfo",mess.getMsg());
            }
        });
    }



}
