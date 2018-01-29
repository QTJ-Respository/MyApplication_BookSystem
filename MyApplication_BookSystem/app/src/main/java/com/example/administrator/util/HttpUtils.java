package com.example.administrator.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/12/26.
 */

public class HttpUtils {

    public static void doPost(final URL url, final String data, final CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try {
                    conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(10*1000);
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setInstanceFollowRedirects(true);
                    conn.connect();
                    if(data!=null&&data.length()>0){
                        OutputStream out=conn.getOutputStream();
                        out.write(data.getBytes());
                        out.close();
                    }
                    callBack.success(FileUtils.formatStreamToString(conn.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.failed("操作出错");
                }finally {
                    if(conn!=null)
                        conn.disconnect();
                }

            }
        }).start();
    }

    public static void doGet(final URL url,final CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn=null;
                try {
                    conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(10*1000);
                    conn.setInstanceFollowRedirects(true);
                    conn.connect();
                    callBack.success(FileUtils.formatStreamToString(conn.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                    callBack.failed("操作出错");
                }finally {
                    if(conn!=null)
                        conn.disconnect();
                }

            }
        }).start();
    }
}
