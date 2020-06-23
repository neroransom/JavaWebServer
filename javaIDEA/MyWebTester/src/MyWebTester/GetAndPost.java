package MyWebTester;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetAndPost {
    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            URL url = new URL(httpurl);
            connection = (HttpURLConnection) url.openConnection();// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);// 设置连接主机服务器的超时时间：15000毫秒
            connection.setReadTimeout(60000);// 设置读取远程返回的数据时间：60000毫秒
            connection.connect();// 发送请求
            if (connection.getResponseCode() == 200) {// 通过connection连接，获取输入流
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));// 封装输入流is，并指定字符集
                StringBuffer sbf = new StringBuffer();// 存放数据
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();// 关闭远程连接
        }
        return result;
    }
    public static String doPost(String httpUrl, String param) {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();// 通过远程url连接对象打开连接
            connection.setRequestMethod("POST");// 设置连接请求方式
            connection.setConnectTimeout(15000);// 设置连接主机服务器超时时间：15000毫秒
            connection.setReadTimeout(60000);// 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setDoOutput(true);// 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoInput(true);// 默认值为：true，当前向远程服务读取数据时，设置为true
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");// 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");// 设置鉴权信息
            os = connection.getOutputStream();// 通过连接对象获取一个输出流
            os.write(param.getBytes());// 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            if (connection.getResponseCode() == 200) {// 通过连接对象获取一个输入流，向远程读取
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
    public static String SendPost(String httpUrl, String param){
        String s=doGet(httpUrl);
        Pattern pattern = Pattern.compile("action=(\"[^\"]*\")");
        Matcher matcher = pattern.matcher(s);
        String temp ="";
        String url ="";
        while(matcher.find()){
            temp =matcher.group();
        }
        String spliturl[] =temp.split("\"");
        temp =spliturl[1];
        String split[] = httpUrl.split("/");
        for(int i=1;i<split.length-1;i++) {
            url+=("/"+split[i]);
        }
        url+="/"+temp;
        String a =doPost("http:"+url,param);
        return a;
    }
}
