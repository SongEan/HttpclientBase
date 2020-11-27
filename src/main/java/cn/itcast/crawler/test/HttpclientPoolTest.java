package cn.itcast.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * ProjectName：itcast-crawler-first
 * DateTime: 2020-11-22 16:42
 */
public class HttpclientPoolTest {
    public static void main(String[] args) {
//        创建连接池管理器
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
//        设置连接数
        poolingHttpClientConnectionManager.setMaxTotal(100);
//        设置每个主机连接数,分配给不同host主机连接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(10);
        System.out.println("$$$$$$1-"+poolingHttpClientConnectionManager.getDefaultMaxPerRoute());
        System.out.println("$$$$$$2-"+poolingHttpClientConnectionManager.getMaxTotal());

        doGet(poolingHttpClientConnectionManager);
        doGet(poolingHttpClientConnectionManager);
    }

    private static void doGet(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager) {
//        从连接池中获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            不关闭httpclient 因为httpclient在连接池中管理
        }

    }
}
