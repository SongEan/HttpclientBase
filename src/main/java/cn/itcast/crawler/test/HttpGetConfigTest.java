package cn.itcast.crawler.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * ProjectName：itcast-crawler-first
 * DateTime: 2020-11-21 20:02
 */
public class HttpGetConfigTest {
    public static void main(String[] args) {
//        创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        创建请求地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
//        配置请求信息
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)//创建连接的最大时间
                .setConnectionRequestTimeout(500)//获取连接的最长时间
                .setSocketTimeout(10 * 1000)//设置数据传输的最长时间
                .build();
//        设置到请求当中
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
//            发起请求
            response = httpClient.execute(httpGet);
//            解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
//            获取响应
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
//            关闭response
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
//                关闭httpclient
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
