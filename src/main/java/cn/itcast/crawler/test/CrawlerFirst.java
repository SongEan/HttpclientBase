package cn.itcast.crawler.test;

import org.apache.http.HttpEntity;
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
 * DateTime: 2020-11-21 16:44
 */
public class CrawlerFirst {
    public static void main(String[] args) throws IOException {
//        1.打开浏览器 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        2.输入网址,发起get请求创建HttpGet对象
        HttpGet get = new HttpGet("http://www.itcast.cn");

//        3.回车发送请求，获取响应
        CloseableHttpResponse httpResponse = httpClient.execute(get);

//        4.解析响应，获取数据
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            HttpEntity responseEntity = httpResponse.getEntity();
            String content = EntityUtils.toString(responseEntity, "utf8");
            System.out.println(content);
        }
    }
}
