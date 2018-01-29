package com.example.administrator.myapplication_booksystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void getMes(View view){
        Intent intent = new Intent(this,MesActivity.class);
        startActivity(intent);
    }
    //看新闻
    public void goNews(View view){
        Intent intent = new Intent(this,NewsActivity.class);
        startActivity(intent);
    }

    public void getWeather(View view){
        Intent intent = new Intent(this,WeatherActivity.class);
        startActivity(intent);
    }

}
