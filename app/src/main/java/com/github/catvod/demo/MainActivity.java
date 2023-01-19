package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.spider.AppLongXia;
import com.github.catvod.spider.AppTT2;
import com.github.catvod.spider.LiteApple;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {

            @Override
            public void run() {
                AppLongXia aidi1 = new AppLongXia();
//                AppTT2 aidi1 = new AppTT2();
               // aidi1.init(MainActivity.this, "http://39.107.101.221:9099/api.php/Videoone");
                String json = aidi1.homeContent(true);
                System.out.println(json);
                System.out.println(aidi1.homeVideoContent());
//                System.out.println(aidi1.categoryContent("20","1",false,new HashMap<>()));
                List list = new ArrayList();
                list.add("124121");
//                list.add("365785");
               // System.out.println(aidi1.detailContent(list));
               // System.out.println(aidi1.searchContent("少年歌行",true));
                String url = "http://39.107.101.221:2025/iphonex/100.php?url=http://v.youku.com/v_show/id_XNTkxOTk0NDI2NA==.html!!!!xcv0873737|http://39.107.101.221:2025/iphonex/400.php?url=http://v.youku.com/v_show/id_XNTkxOTk0NDI2NA==.html!!!!xcv0873737";
                System.out.println(aidi1.playerContent("",url,new ArrayList<String>()));

            }
        }).start();
    }
}

//{"key":"csp_LiteApple","name":"🍎小苹果(ijk)","type":3,"api":"csp_LiteApple","searchable":1,"quickSearch":1,"filterable":1,"playerType":1,"jar": "https://gitcdn.top/https://raw.githubusercontent.com/smallgirl/TvJar/master/spider/litleAppleLongXia.jar;md5;10373e843a551fdca047a5ddd5e3d1eb"},
//{"key":"AppLongXia","name":"🦞龙虾视频","type":3,"api":"csp_AppLongXia","searchable":1,"quickSearch":1,"filterable":1,"playerType":1,"jar": "https://gitcdn.top/https://raw.githubusercontent.com/smallgirl/TvJar/master/spider/litleAppleLongXia.jar;md5;10373e843a551fdca047a5ddd5e3d1eb"},
//{"key":"AppTT2","name":"📍图图影视","type":3,"api":"csp_AppTT2","searchable":1,"quickSearch":1,"filterable":1,"ext":"http://124.248.66.89:7788","jar": "https://gitcdn.top/https://raw.githubusercontent.com/smallgirl/TvJar/master/spider/litleAppleLongXia.jar;md5;10373e843a551fdca047a5ddd5e3d1eb"},