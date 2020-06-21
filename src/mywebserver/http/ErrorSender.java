package mywebserver.http;

import mywebserver.http.HttpResponse;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ErrorSender {
    HttpResponse response;
    public ErrorSender(HttpResponse resp) throws IOException {
        response = resp;
    }

    public void sendHtml404() throws IOException {
        File f = new File("web/site/404.html");
        DataInputStream din = new DataInputStream(new FileInputStream(f));
        int len = (int) f.length();
        byte[] buf = new byte[len];
        din.readFully(buf);
        response.getOutputStream().writeBytes("HTTP/1.1 200 \r\n\r\n");
        response.getOutputStream().write(buf);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


    public void sendHtml404Servlet() throws IOException {
        File f = new File("web/site/404.html");
        DataInputStream din = new DataInputStream(new FileInputStream(f));
        int len = (int) f.length();
        byte[] buf = new byte[len];
        din.readFully(buf);
        response.getOutputStream().write(buf);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
