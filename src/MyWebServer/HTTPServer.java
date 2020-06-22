package mywebserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {
    public static Map<String, String> servletMap = new HashMap<>();

    public static void main(String a[]) throws Exception {

        System.out.println("Loading configuration...");
        //String config = readFiletoString("web/WEB-INF/web.xml");
        String config = readFiletoString("web/WEB-INF/web.xml");
        loadXML(config);
        config.toString();

        final int httpd = 8081;
        ServerSocket ssock = new ServerSocket(httpd);
        System.out.println("Listening the port 8081 locally...");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("client has made socket connection");

            OneConnection client = new OneConnection(sock);
            new Thread(client).start();

        }

//        try {
//            ssock.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("HttpServer close");
    }


    public static String readFiletoString(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            if (file.isFile()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                String s = null;
                while ((s = bufferedReader.readLine()) != null) {
                    content+=s;
                }
                read.close();
            } else {
                System.out.println("找不到XML");
            }
        } catch (Exception e) {
            System.out.println("读取XML失败");
            e.printStackTrace();
        }
        return content;
    }

    public static void loadXML(String content) {
        servletMap.clear();
        int begin = 0;
        String name;
        while ((begin = content.indexOf("<servlet>", begin)) != -1) {
            begin += 9;
            String servletName = content.substring(content.indexOf("<servlet-name>", begin)+14, content.indexOf("</servlet-name>", begin));
            String classMap = content.substring(content.indexOf("<servlet-class>", begin) + 15, content.indexOf("</servlet-class>",  begin));

            int mapIndex = content.indexOf("<servlet-name>"+servletName+"</servlet-name>",begin);
            String urlPattern = content.substring(content.indexOf("<url-pattern>", mapIndex)+ 13, content.indexOf("</url-pattern>", mapIndex));
            servletMap.put(urlPattern,classMap);
        }
    }


}
