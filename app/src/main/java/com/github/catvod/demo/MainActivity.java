package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.spider.AppTT2;
import com.github.catvod.spider.LiteApple;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {

            @Override
            public void run() {
                AppTT2 aidi1 = new AppTT2();
                aidi1.init(MainActivity.this, "http://124.248.66.89:7788");
                String json = aidi1.homeContent(true);
                System.out.println("111");
                System.out.println(json);
//                JSONObject homeContent = null;
//                try {
//                    homeContent = new JSONObject(aidi1.homeVideoContent());
//                   // System.out.println(homeContent.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                List<String> ids = new ArrayList<String>();
//                ids.add("118769");
//                System.out.println(aidi1.detailContent(ids));
            }
        }).
                start();
    }
}


// https://gitcdn.top/https://raw.githubusercontent.com/smallgirl/TvJar/master/spider/litleApple.jar;md5;25e8492cf8dc88ce2a7aaaac9eecbf7b
// https://gitcdn.top/https://raw.githubusercontent.com/smallgirl/TvJar/master/spider/json/tvbox.json