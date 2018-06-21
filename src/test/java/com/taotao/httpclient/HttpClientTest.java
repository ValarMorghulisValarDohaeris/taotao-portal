package com.taotao.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hao on 2018/6/21.
 */
public class HttpClientTest {

    @Test
    public void doGet() throws IOException {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET对象
        HttpGet get = new HttpGet("http://www.sogou.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    @Test
    public void doGetWithParam() throws URISyntaxException, IOException {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com/web");
        uriBuilder.addParameter("query","科比");
        HttpGet get = new HttpGet(uriBuilder.build());
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    @Test
    public void doPost() throws Exception{
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个POST对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
        response.close();
        httpClient.close();
    }
    
    @Test
    public void doPostWithParam() throws Exception{
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个POST对象
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
        //创建一个Entity。模拟一个表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username","Kobe"));
        kvList.add(new BasicNameValuePair("password","4717"));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList);
        //设置请求的内容
        post.setEntity(entity);
        //执行post请求
        CloseableHttpResponse response = httpClient.execute(post);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
        response.close();
        httpClient.close();
    }
}
