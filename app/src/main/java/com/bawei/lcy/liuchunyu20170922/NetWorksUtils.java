package com.bawei.lcy.liuchunyu20170922;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by LCY on 2017/9/22.
 */

public class NetWorksUtils {
    //解析的方法
    public String getJsonUrlJiexi(String jsonStr){
        URL url=null;
        HttpURLConnection httpURLConnection=null;
        String JsonUrl="";
        try {
            //进行网络解析
            url=new URL(jsonStr);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            //获取响应码
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode==200){
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] bytes = new byte[1024];
                int i=0;
                while ((i=inputStream.read(bytes))!=-1){
                    JsonUrl +=new String(bytes,0,i);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonUrl;
    }
}
