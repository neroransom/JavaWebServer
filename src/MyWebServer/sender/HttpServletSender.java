package mywebserver.sender;

import mywebserver.HttpServer;
import mywebserver.servlet.HttpServlet;
import mywebserver.servlet.HttpServletRequest;
import mywebserver.servlet.HttpServletResponse;
import mywebserver.servlet.ServletException;

import java.io.IOException;

public class HttpServletSender implements Runnable {
    HttpServletResponse response;

    public HttpServletSender(HttpServletResponse resp)
    {
        response = resp;
    }

    @Override
    public void run() {
        try {
            response.getOutputStream().writeBytes("HTTP/1.1 200 OK\r\n\r\n");
            String value = HttpServer.configuration.get(response.getRequest().getUrl());
            Class clazz = Class.forName(value);
            HttpServlet servlet = (HttpServlet) clazz.newInstance();
            servlet.service(new HttpServletRequest(response.getRequest()), response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
