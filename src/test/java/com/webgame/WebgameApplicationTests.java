package com.webgame;

import com.alibaba.fastjson.JSONObject;
import com.webgame.utils.HttpClientUtil;
import com.webgame.utils.OkHttpUtil;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class WebgameApplicationTests {

    String TX_URL =  "http://116.233.72.161:7777//随便写写的实验.exe";
    @Test
    void Test() throws IOException {

        String res1 = HttpClientUtil.doPost(TX_URL, null);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","这里是CodeXu！");
        String res2 = OkHttpUtil.doPost(TX_URL,jsonObject.toJSONString());

        //System.out.println(res1);
        System.out.println("======================================================================");
        System.out.println(res2);


    }


    @Test
    public void okhttpTest2() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("hello","嘿！这里是CodeXu！");

        // post 请求
        // 1. new 一个 okhttp对象
        OkHttpClient client = new OkHttpClient();

        // 2. 构建FormBody,传入参数
        FormBody formBody = new FormBody.Builder()
                //.add("username", "admin")
                .add("hello", "嘿！这里是CodeXu！")
                .build();

        // 3. 构建Request,将FormBody作为Post方法的参数传入
        final Request request = new Request.Builder()
                //.url("http://116.233.72.161:7777/真动态网页.bat")
                //.url("http://101.35.17.233:8081/test")
                .url("http://116.233.72.161:7777//随便写写的实验.exe")
                .post(formBody)

                .build();
        // 4. 将Request封装为Call
        Call call = client.newCall(request);

        // 5. 调用请求,
        Response execute = call.execute();
        ResponseBody body = execute.body();
        String res = body.string();
        System.out.println(res);

    }

}
