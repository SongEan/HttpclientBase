package cn.itcast.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * ProjectName：itcast-crawler-first
 * DateTime: 2020-11-21 20:51
 */
public class HttpPostTest {
    public static void main(String[] args) {
//        创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        创建post对象
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");
        CloseableHttpResponse response = null;
        try {
//        执行请求，获取响应
            response = httpClient.execute(httpPost);
//            解析响应
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            System.out.println(content.length());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
