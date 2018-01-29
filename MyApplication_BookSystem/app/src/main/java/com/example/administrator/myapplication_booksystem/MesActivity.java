package com.example.administrator.myapplication_booksystem;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MesActivity extends Activity {

    private ContentResolver resolver;
    private SmsObserver smsObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes);
        resolver = getContentResolver();
        //监听发送信息
        Uri uri = Uri.parse("content://sms");
        smsObserver = new SmsObserver(handler);
        resolver.registerContentObserver(uri,true,smsObserver);
    }
    public void getMes(View view){
        readSms(Uri.parse("content://sms"));
    }
    private void readSms(Uri uri) {
        String[] array = new String[]{"address","body","date"};
        Cursor cursor = resolver.query(uri, array, null, null, null);
        //获取所有列名
        String str = "";
        String[] cols = cursor.getColumnNames();

        while (cursor.moveToNext()) {
            for (int i = 0; i < cols.length; i++) {
                Log.i("短信", cols[i] + ":" + cursor.getString(cursor.getColumnIndex(cols[i])));
                str += cols[i] + ":" + cursor.getString(cursor.getColumnIndex(cols[i])) + "\n";
            }
        }
        cursor.close();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,"搜索完毕",Toast.LENGTH_SHORT).show();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收信息
            switch (msg.what){
                case 1:readSms(Uri.parse("content://sms/outbox"));
                    break;
            }
        }
    };
    //构建接收短信的观察者
    private class SmsObserver extends ContentObserver {
        public SmsObserver(Handler handler) {
            super(handler);
        }
        //监听:当改变就发出信息
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            handler.sendEmptyMessage(1);
        }
    }
}
