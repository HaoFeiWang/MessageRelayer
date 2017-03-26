package com.whf.messagerelayer.utils;

import com.whf.messagerelayer.confing.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by WHF on 2017/3/26.
 */

public class SmsRelayerManager {
    /**
     * 发送短信至目标手机号
     *
     * @param objectMobile 目标手机号
     * @param content      短信内容
     */
    public static void relaySms(String objectMobile, String content) {
        if (objectMobile != null) {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(objectMobile, null, content, null, null);
        }
    }

    /**
     * 单条短信发送,智能匹配短信模板
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,仅支持单号码发送
     * @return json格式字符串
     */
    public static String realySmsProxy(String text, String mobile) {
        Map<String, String> params = new HashMap<>();//请求参数集合
        params.put("apikey", Constant.API_KEY);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }


    private static String requestTemplet(long id){
        Map<String, String> params = new HashMap<>();//请求参数集合
        params.put("apikey", Constant.API_KEY);
        params.put("tpl_id", ""+id);
        return post("URL：https://sms.yunpian.com/v2/tpl/get.json",params);
    }

    /**
     * HttpPost请求
     * @param url
     * @param params
     * @return
     */
    private static String post(String url,Map<String,String> params){
        OkHttpClient httpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body =builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            return httpClient.newCall(request).execute().body().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
