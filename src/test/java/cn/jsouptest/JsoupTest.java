package cn.jsouptest;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ Idea IDEA
 * java version "1.8.0_91"
 * Author: Ean Song
 * ProjectName：itcast-crawler-first
 * DateTime: 2020-11-23 11:12
 */
public class JsoupTest {

    //    通过dom获取元素

    /**
     * 解析URL
     */
    @Test
    void urlTest() {
        try {
            Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
            String title = document.getElementsByTag("title").first().text();
            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析字符串
     */
    @Test
    void stringTest() {
        try {
//            使用工具类读取文件
            String string = FileUtils.readFileToString(
                    new File("/Users/songshijie/Downloads/资料/资料/01资料/正则表达式30分钟入门教程_脚本之家.html"), "utf8");
            Document document = Jsoup.parse(string);
            String title = document.getElementsByTag("title").first().text();
            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析文件
     */
    @Test
    void fileTest() {
        try {
            Document document = Jsoup.parse(new File("/Users/songshijie/Downloads/资料/资料/01资料/正则表达式30分钟入门教程_脚本之家.html"), "utf8");
            String title = document.getElementsByTag("title").first().text();
            System.out.println(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过值定位元素
     *
     * @throws Exception
     */
    @Test
    void elementTest() throws Exception {
        Document document = Jsoup.parse(
                new File("/Users/songshijie/Downloads/资料/资料/01资料/正则表达式30分钟入门教程_脚本之家.html"), "utf8");
//        根据id获取元素
        Element elementById = document.getElementById("introduction");
        String text = elementById.text();
        System.out.println(text);
//        标签获取元素
        String spanText = document.getElementsByTag("span").first().text();
        System.out.println(spanText);
//        class值获取元素
        String importantNote = document.getElementsByClass("important note").first().text();
        System.out.println(importantNote);

        System.out.println(document.getElementsByAttribute("target").size());
//        通过属性值获取元素
        System.out.println(document.getElementsByAttributeValue("href", "http://www.jb51.net").first().text());
    }

    /**
     * 通过元素获取其他属性值
     *
     * @throws IOException
     */
    @Test
    void getData() throws IOException {
        Document document = Jsoup.parse(
                new File("/Users/songshijie/Downloads/资料/资料/01资料/正则表达式30分钟入门教程_脚本之家.html"), "utf8");
        Element element = document.getElementById("skipContents");
//        根据元素获取id值
        System.out.println(element.id());
//        根据元素获取class值
        System.out.println(element.className());
//        根据元素获取属性值
        System.out.println(element.attr("class"));
//        获取文本值
        System.out.println(element.text());
    }

    //    通过selector选择器获取元素
    @Test
    void selectorTest() throws IOException {
        //            使用工具类读取文件
        Document document = Jsoup.parse(
                new File("/Users/songshijie/Downloads/资料/资料/01资料/正则表达式30分钟入门教程_脚本之家.html"), "utf8");
//       通过标签查找
        Elements elements = document.select("span");
        for (Element element : elements) {
            System.out.println(element.text());
        }
//        根据id获取元素
        Elements elements1 = document.select("#meta");
        System.out.println(elements1.first());

//        通过class获取元素 <span class="part"> 输入class值
        final Elements elements2 = document.select(".part");
        Object[] objects = elements2.toArray();
        System.out.println(objects[3]);

//        根据属性获取元素[属性值]
        Elements elements3 = document.select("[href]");
        for (Element element : elements3) {
            System.out.println(element.text());
        }

        System.out.println("***********************");
//        根据属性值获取元素<p id="giveMe30Minutes" class="important note">
        Elements elements4 = document.select("[class=important note]");
        for (Element element : elements4) {
            System.out.println(element.text());
        }
    }
}
