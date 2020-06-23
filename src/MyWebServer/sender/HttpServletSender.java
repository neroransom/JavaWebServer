package mywebserver.sender;

import mywebserver.HttpServer;
import mywebserver.jsp.JspTranslation;
import mywebserver.servlet.HttpServlet;
import mywebserver.servlet.HttpServletRequest;
import mywebserver.servlet.HttpServletResponse;
import mywebserver.servlet.ServletException;


import java.io.File;
import java.io.IOException;

public class HttpServletSender implements Runnable {
    HttpServletResponse response;

    public HttpServletSender(HttpServletResponse resp)
    {
        response = resp;
    }

    @Override
    public void run() {
        if(response.getRequest().getUrl().endsWith(".jsp"))
        {
            try {
                response.getOutputStream().writeBytes("HTTP/1.1 200 OK\r\n\r\n");
                new JspTranslation().ofThe(response.getRequest().getUrl());
                String fileName = response.getRequest().getUrl();
                String value = "mywebserver.jsp.jspclass." + fileName.substring(1, fileName.indexOf(".jsp")) + "_jsp";
                Class aClass = Class.forName(value);
                HttpServlet servlet = (HttpServlet) aClass.newInstance();
                servlet.service(new HttpServletRequest(response.getRequest()), response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
        }
        else {
            try {
                response.getOutputStream().writeBytes("HTTP/1.1 200 OK\r\n\r\n");
                String value = HttpServer.servletMap.get(response.getRequest().getUrl());
                Class aClass = Class.forName(value);
                HttpServlet servlet = (HttpServlet) aClass.newInstance();
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
}
