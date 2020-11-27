package cn.itcast.crawler.test;

import com.sun.javafx.fxml.builder.URLBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * ProjectName：itcast-crawler-first
 * DateTime: 2020-11-21 20:02
 */
public class HttpGetParamsTest {
    public static void main(String[] args) throws URISyntaxException {
//        创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        创建请求地址
//        创建URiBuilder
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
//        设置参数
        uriBuilder.setParameter("keys", "Java");
        HttpGet httpGet = new HttpGet(uriBuilder.build());
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
