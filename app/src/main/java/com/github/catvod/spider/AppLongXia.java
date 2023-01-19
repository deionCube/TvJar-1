package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;

import com.github.catvod.crawler.Spider;
import com.github.catvod.utils.okhttp.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * Author: @SDL
 */
public class AppLongXia extends Spider {

    private String siteUrl = "http://39.107.101.221:9099";
    private String version = "2.3.3";

    private static final String longXiaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDWHmmu0aFU2m1ZnQb09+/QQRnuy3GmcwnTnFwPLR/CsfbsxiNQLULpdwhC1h1U/3yEyc2kZ549+X3iYFed3tHa0NLntcSw6w6IcEAgiaeHasRlzh98IgEai9XChbhLAy7a/s4HnhFXJCsg5/FqrjZ/FuEOhbCN3gWcc6Aly+OZhwIDAQAB";

    private static final String longXiaPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL4cSBLgAtKxA3brZ73KA6/uagCFNBqh3+GVWZGyKlrChUrrqcBHmV/fZcH/oVIwZHCxv0rnOesTE7WZhzgbbUEsBDK8W8+paIwQI3Pvpp3JzEvduOfS/4G4EPYpKiGE3+vK9q7h+9xVv6FS8uzKAe6uS98qDgHad46cMJVkYR0DAgMBAAECgYAZMBAQiCN3nMJTwC63g4tnhNQAi6Vynd3Wun3qgst/yOP2IDEWa6YTgLqvsFnEVOsJz1MD7ozK63UiC1xR/7hCvqTziaV7g0lCB9Gk9ZMRIpS8pPtfnDSMjeYzMCQWvdRMOf8BkFp7SISCtlQRm9VKKRKMZ27zH3BvIvTed4tYoQJBAOw7Gda7L2CXBdKfpRK6KgUVkki/d9qEcm1kS9TFAxrQcEWdijF7l9w8LnaBPC0uaRKmEB4/uiOMXXN89Vo6CnECQQDOBR57KgMAAKDzFIRDyegl1Y6xTpEIi0/YlTlPyZmYpG2vS6czG+OQfJoT/w/vBw7mMGTR3IiQwF2qqYACUNCzAkEA60suwGgvl3i1jwX+iLmu8uN6kkVL3vZ/dyAoO+SD5ChrO5vgMstVBkNXUCgHRUVt2OpZMZfuEkxUJJz5UQZwsQJAUCLFnHrW9+VGtcbBO+0Jk83h1y4MVBp8UG6bAGIWkL1EvO7cdpDej5EoDack94DzVq50SP1TUZrB1GRiGoR86QJAfy9NP2vDtk8MyjIUWjpYTTUhjMk/6WurIBtwEzE56QLFLshm1KwEFh25qrdI4vXChQQKXAgu17b+I7qfYh2IMA==";

    private static final String longXiaParsePrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM6T6V5lfKE5miEMR88NalNn6CvzQC+PR8bH0ipUege8D/tFgjh22J3Yh1Ibgl1zsoifz3eF8/IKWZ6Hui7iUeOvbZteCuz7IBLFvnbPsNCP7SbLGPYRasVp3zqwt2NV72aZ/bI9pLczK2+b0R+KJXZ5V6fmVFx/QWWAo+NmkerVAgMBAAECgYA0yBQjm1iptRcNhJ7AZ5QYNX9b424t+LUFND8ds7HuUf3fXNY16R/VzOJed0rq58nhILwYtPAskrptSA6pNghn56dike27vANR6mTaYv+0o1FS/+lGQ1UohSAnzVw30L/tJzrobvvU4joXYjb95typAOiIvcOh8WAuwQRU7I5MAQJBAPh/Ji6mKbllYHAn+sHdE0cGm8QISZz+7dvZQQ8L7MzJG1eUYZXCXQ9Fiqrk3kLNwq6IW+eruBsH4HJa9LNyjwECQQDU0Lvy/EnnQNITryXQ20gxR+x4e9Nak+4GJbL6JFf9kqCUhei2ni8t/RO7wp+jrKUy2kdzGlRL+cv5dgxzv+/VAkBQua2DtgMT8TT0+mfhlpnultz/P9n6IG7Q3rDd3Hfexu4U82UIK43jqimz/omdlg5KeI2yovw5+8MUIywfJ3YBAkBanIVp8AGHdRH9T5XKV5NlaDpHEnHrHxE5jNOnrdHJJaU5l8p99twfuKGuUC+ogNnVzRqe55b8wl8W2Cx1HEQBAkAzWKejO1OWmdf+VwgUwShiRMvlNvzO7iPoyE6B4DrLM5dAMk8BN4Cyk1T/4pDfcj9FCydDk3fCmtPA0DWngU6A";

    private static String deviceId = "17b39b7cf709ceed";
    // private static String deviceId = getStringRandom(16);

    private HashMap<String, String> getHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", "okhttp/3.14.9");
        return hashMap;
    }

    private String longXiaCSRF(String data) {
        return URLEncoder.encode(RSAEncrypt.encryptByPublicKey(data, longXiaPublicKey));
    }

    private String longXiaCSRF() {
        return longXiaCSRF(String.valueOf(new Date().getTime() / 1000L));
    }

    @Override
    public void init(Context context, String extend) {
        super.init(context, extend);
        if (!TextUtils.isEmpty(extend)) {
            String str[] = extend.split("|");
            siteUrl = str[0];
            version = str[1];
        }
    }

    @Override
    public String homeContent(boolean filter) {
        try {
            String url = siteUrl + "/api.php/Videoone/nav?appid=10001&device_id=" + deviceId + "&csrf=" + longXiaCSRF();
            String data = OkHttpUtil.string(url, getHeaders());
            JSONObject jsonObject = new JSONObject(decryptResponse(data));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject result = new JSONObject();
            result.put("class", jsonArray);
            return result.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    @Override
    public String homeVideoContent() {
        try {
            JSONArray videos = new JSONArray();
            try {
                String url = siteUrl + "/api.php/Videoone/advert?appid=10001&device_id=" + deviceId + "&csrf=" + longXiaCSRF();
                String data = OkHttpUtil.string(url, getHeaders());
                JSONObject jsonObject = new JSONObject(decryptResponse(data));
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject blockObj = jsonArray.getJSONObject(i);
                    JSONObject v = new JSONObject();
                    v.put("vod_id", blockObj.getString("req_content"));
                    v.put("vod_name", blockObj.getString("name"));
                    v.put("vod_pic", blockObj.getString("content"));
                    v.put("vod_remarks", "");
                    videos.put(v);
                }
            } catch (Exception e) {
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        try {
            String url = siteUrl + "/api.php/Videoone/video?tid=" + tid + "&pg=" + pg + "&appid=10001&device_id=" + deviceId + "&csrf=" + longXiaCSRF();
            Set<String> keys = extend.keySet();
            for (String key : keys) {
                String val = extend.get(key).trim();
                if (val.length() == 0)
                    continue;
                url += "&" + key + "=" + URLEncoder.encode(val);
            }
            String content = OkHttpUtil.string(url, getHeaders());
            JSONArray jsonArray = new JSONObject(decryptResponse(content)).getJSONArray("data");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", vObj.getString("vod_name"));
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();

            result.put("page", pg);
            result.put("pagecount", 500);
            result.put("limit", 20);
            result.put("total", 10000);
            result.put("list", videos);
            return result.toString();
        } catch (Throwable th) {

        }
        return "";
    }

    @Override
    public String detailContent(List<String> ids) {
        try {

            String time = String.valueOf(new Date().getTime() / 1000L);
            JSONObject object = new JSONObject();
            String json = object.toString();
            String ck = longXiaCSRF(json);
            String url = siteUrl + "/api.php/Videoone/videoDetail?id=" + ids.get(0) + "&appid=10001&device_id=" + deviceId + "&ck=" + ck + "&csrf=" + longXiaCSRF(time) + "&version=" + version;
            String content = OkHttpUtil.string(url, getHeaders());
            JSONObject dataObject = new JSONObject(content);
            String infoJson = decryptResponse(dataObject.getString("data"));
//            System.out.println(infoJson);
            JSONObject result = new JSONObject();
            JSONArray list = new JSONArray();

            JSONObject videoInfo = new JSONObject(infoJson).getJSONObject("data").getJSONObject("vod_info");

            JSONObject vodAtom = new JSONObject();
            vodAtom.put("vod_id", videoInfo.getString("vod_id"));
            vodAtom.put("vod_name", videoInfo.getString("vod_name"));
            vodAtom.put("vod_pic", videoInfo.getString("vod_pic"));
            //   vodAtom.put("type_name", videoInfo.getString("subCategory"));
            vodAtom.put("vod_year", videoInfo.getString("vod_year"));
            vodAtom.put("vod_area", videoInfo.getString("vod_area"));
            vodAtom.put("vod_remarks", videoInfo.getString("vod_remarks"));
            vodAtom.put("vod_actor", videoInfo.getString("vod_actor"));
            vodAtom.put("vod_director", videoInfo.getString("vod_director"));
            vodAtom.put("vod_content", videoInfo.getString("vod_content").trim());

            JSONArray episodes = dataObject.getJSONArray("video_list");
            List<String> parsesList = new ArrayList<>();
            List<String> showList = new ArrayList<>();
            for (int i = 0; i < episodes.length(); i++) {
                List<String> parses = new ArrayList<>();
                String show = episodes.getJSONObject(i).getString("code");
                showList.add(show);
                String videoUrl = episodes.getJSONObject(i).getString("url");
                String[] urls = videoUrl.split("\\#");
                JSONArray parseArray = episodes.getJSONObject(i).getJSONArray("parse_api");
                for (int h = 0; h < urls.length; h++) {
                    String urlName = urls[h].split("\\$")[0];
                    String urlKey = urls[h].split("\\$")[1];
                    urlName = urlName + "$";
                    if (urlKey.contains(".m3u8") || urlKey.contains(".mp4")) {
                        urlName = urlName + urlKey;
                    } else {
                        for (int j = 0; j < parseArray.length(); j++) {
                            if (j > 0) {
                                urlName = urlName + "|";
                            }
                            urlName = urlName + parseArray.getString(j) + urlKey + "!!!!" + episodes.getJSONObject(i).getString("user_agent");
                        }
                    }
                    System.out.println(urlName);
                    parses.add(urlName);
                }
                parsesList.add(TextUtils.join("#", parses));
            }
            vodAtom.put("vod_play_from", TextUtils.join("$$$", showList));
            vodAtom.put("vod_play_url", TextUtils.join("$$$", parsesList));
            list.put(vodAtom);
            result.put("list", list);
            return result.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        try {
            if (id.contains(".m3u8") || id.contains(".mp4")) {
                JSONObject result = new JSONObject();
                result.put("parse", 0);
                result.put("playUrl", "");
                result.put("url", id);
                return result.toString();
            }
            //for 循环 解析
            String[] urls = id.split("\\|");
            String url = "";
            String header = null;
            // System.out.println(id);
            //http://39.107.101.221:2025/iphonex/100.php?url=https://v.qq.com/x/cover/ceusrubximxm0g8/q00237bibub.html!!!!xcv0873737|http://39.107.101.221:2025/iphonex/400.php?url=https://v.qq.com/x/cover/ceusrubximxm0g8/q00237bibub.html!!!!xcv0873737|http://39.107.101.221:2025/iphonex/200.php?url=https://v.qq.com/x/cover/ceusrubximxm0g8/q00237bibub.html!!!!xcv0873737|http://39.107.101.221:2025/iphonex/rongxing.php?url=https://v.qq.com/x/cover/ceusrubximxm0g8/q00237bibub.html!!!!xcv0873737
            for (int h = 0; h < urls.length; h++) {
                String[] urlAndHeader = urls[h].split("\\!!!!");
                HashMap<String, String> headers = getHeaders();
                headers.put("User-Agent", urlAndHeader.length > 1 ? urlAndHeader[1] : "okhttp/3.14.9");
                String parseUrl = urlAndHeader[0] + "&appId=10001&device_id=" + deviceId + "&version=" + version;
                JSONObject dataObject = new JSONObject(OkHttpUtil.string(parseUrl, headers));
                if (dataObject.optInt("encryption") == 1) {
                    String data = dataObject.getString("data");
                    System.out.println(data);
                    String json = RSAEncrypt.decryptByPrivateKey(data, longXiaParsePrivateKey);
                    JSONObject jsonObject = new JSONObject(json);
                    url = jsonObject.optString("url");
                    if (!TextUtils.isEmpty(url)) {
                        JSONObject headerJson = jsonObject.optJSONObject("header");
                        if (headerJson != null && headerJson.length() > 0) {
                            header = headerJson.toString();
                        }
                        break;
                    }
                } else {
                    JSONObject jsonObject = dataObject.getJSONObject("data");
                    ;
                    url = jsonObject.optString("url");
                    if (!TextUtils.isEmpty(url)) {
                        JSONObject headerJson = jsonObject.optJSONObject("header");
                        if (headerJson != null && headerJson.length() > 0) {
                            header = headerJson.toString();
                        }
                        break;
                    }
                }
            }
            JSONObject result = new JSONObject();
            result.put("parse", 0);
            result.put("playUrl", "");
            result.put("url", url);
            if (header != null) {
                result.put("header", header);
            }
            return result.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    @Override
    public String searchContent(String key, boolean quick) {
        try {
            String url = siteUrl + "/api.php/Videoone/search?page=1&text=" + URLEncoder.encode(key) + "&device_id=" + deviceId + "&csrf=" + longXiaCSRF();
            String content = decryptResponse(OkHttpUtil.string(url, getHeaders()));
            JSONObject dataObject = new JSONObject(content);
            JSONArray jsonArray = dataObject.getJSONArray("data");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", vObj.getString("vod_name"));
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    protected String decryptResponse(String src) {
        return RSAEncrypt.decryptByPrivateKey(src, longXiaPrivateKey);
    }


    /**
     * @param length 长度
     * @return String    返回类型
     * @Description: 生成随机数字和字母区分大小写
     */
    public static String getStringRandom(int length) {

        StringBuilder val = new StringBuilder();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            } else {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
}