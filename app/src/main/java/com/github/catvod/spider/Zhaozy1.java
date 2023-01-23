package com.github.catvod.spider;

import android.content.Context;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.utils.okhttp.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Zhaozy1 extends Spider {
    private static final Pattern aliyun = Pattern.compile("(https://www.aliyundrive.com/s/[^\"]+)");
    private PushAgent pushAgent;
    private final String siteUrl = "https://zhaoziyuan.la/";
    private final String CHROME = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private String username = "nikalo8893@bitvoo.com";
    private String password = "P@ssw0rd";

    public String detailContent(List<String> list) {
        try {
            Pattern pattern = aliyun;
            if (pattern.matcher(list.get(0)).find()) {
                return pushAgent.detailContent(list);
            }
            Matcher matcher = pattern.matcher(OkHttpUtil.string(siteUrl + list.get(0), getHeader()));
            if (!matcher.find()) {
                return "";
            }
//            list.set(0, matcher.group(1));
//            return pushAgent.detailContent(list);
            //替换videoId 改动部分 ----
            String videoId = list.get(0);
            list.set(0, matcher.group(1));
            String json = pushAgent.detailContent(list);
            if ("".equals(json)) {
                return "";
            }
            JSONObject result = new JSONObject(json);
            JSONArray jsonList = result.getJSONArray("list");
            JSONObject jsonObject = jsonList.getJSONObject(0);
            jsonObject.put("vod_id", videoId);
            //替换videoId 改动结束
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    public void init(Context context, String str) {
        super.init(context, str);
        pushAgent = new PushAgent();
        String strs[] = str.split("\\$\\$\\$");
        String url = strs[0];
        if (strs.length > 2) {
            username = strs[1];
            password = strs[2];
        }
        pushAgent.init(context, url);
    }

    public String playerContent(String str, String str2, List<String> list) {
        return pushAgent.playerContent(str, str2, list);
    }

    private Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", CHROME);
        headers.put("Referer", siteUrl);
        headers.put("Cookie", getCookie());
        return headers;
    }

    private String getCookie() {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", CHROME);
        headers.put("Referer", siteUrl + "login.html");
        headers.put("Origin", siteUrl);
        Map<String, List<String>> resp = new HashMap<>();
        postString(siteUrl + "logiu.html", params, headers, resp);
        StringBuilder sb = new StringBuilder();
        for (String item : resp.get("set-cookie")) sb.append(item.split(";")[0]).append(";");
        return sb.toString();
    }

    private String postString(String url, Map<String, String> paramsMap, Map<String, String> headerMap, Map<String, List<String>> respHeaderMap) {

        Request.Builder builder = new Request.Builder().url(url)
                .post(getRequestBody(paramsMap));
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                builder.addHeader(key, headerMap.get(key));
            }

        }
        Request request = builder.build();
        Call call = OkHttpUtil.defaultClient().newCall(request);
        try {
            Response response = call.execute();
            if (respHeaderMap != null) {
                respHeaderMap.clear();
                respHeaderMap.putAll(response.headers().toMultimap());
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private RequestBody getRequestBody(Map<String, String> paramsMap) {

        FormBody.Builder formBody = new FormBody.Builder();
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                formBody.add(key, paramsMap.get(key));
            }
        }
        return formBody.build();
    }

    private Pattern regexVid = Pattern.compile("(\\S+)");

    public String searchContent(String key, boolean quick) {
        try {
            String url = "https://zhaoziyuan.la/so?filename=" + URLEncoder.encode(key);
            Document docs = Jsoup.parse(OkHttpUtil.string(url, getHeader()));
            JSONObject result = new JSONObject();
            JSONArray videos = new JSONArray();
            Elements list = docs.select("div.li_con div.news_text");
            for (int i = 0; i < list.size(); i++) {
                Element doc = list.get(i);
                String sourceName = doc.select("div.news_text a h3").text();
                if (sourceName.contains(key)) {
                    String list1 = doc.select("div.news_text a").attr("href");
                    Matcher matcher = regexVid.matcher(list1);
                    if (matcher.find()) {
                        JSONObject v = new JSONObject();
                        String group = matcher.group(1);
                        String cover = "https://inews.gtimg.com/newsapp_bt/0/13263837859/1000";
                        String remark = doc.select("div.news_text a p").text();

                        v.put("vod_name", sourceName);
                        v.put("vod_remarks", remark);
                        v.put("vod_id", group);
                        v.put("vod_pic", cover);
                        videos.put(v);
                    }
                }
            }
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }
}