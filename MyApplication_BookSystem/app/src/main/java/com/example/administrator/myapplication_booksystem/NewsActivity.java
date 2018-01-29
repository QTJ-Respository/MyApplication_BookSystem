package com.example.administrator.myapplication_booksystem;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends Activity {

    private ListView listView;
    private DrawerLayout drawerLayout;
    private TextView textView;

    //片段管理器
    private FragmentManager fm;
    //片段事务对象
    private FragmentTransaction trans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将activity设置为全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_news);
        initView();

        fm = getFragmentManager();
        trans = fm.beginTransaction();
        trans.add(R.id.mainFrame,new context());
        trans.commit();
    }

    //=======================侧滑菜单
    private void initView()
    {
        listView=(ListView) findViewById(R.id.v4_listview);
        drawerLayout=(DrawerLayout) findViewById(R.id.v4_drawerlayout);
        textView=(TextView) findViewById(R.id.v4_text);
        initDate();
    }

    private void initDate(){
        final List<String> list = new ArrayList<String>();
        list.add("个人");
        list.add("图书管理");
        list.add("借阅信息");
        list.add("学生管理");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                fm = getFragmentManager();
                trans = fm.beginTransaction();
                //textView.setText(list.get(position));
                switch (list.get(position)){
                    case "个人":
                        trans = trans.replace(R.id.mainFrame, new context());
                        trans.commit();
                        break;
                    case "退出":;
                }
                showDrawerLayout();
            }
        });
        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }

    private void showDrawerLayout() {
        if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.openDrawer(Gravity.LEFT);
        } else {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }



}
