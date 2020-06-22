package mywebserver.servlet;

import mywebserver.HttpServer;

import java.io.IOException;

public class RequestDispatcher {
    HttpServletRequest request;
    HttpServletResponse response;
    String next;

    RequestDispatcher(String next)
    {
        this.next = next;
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, InstantiationException, ServletException, IllegalAccessException {
        if(next.endsWith(".jsp"))
        {

        }
        else{
            String value = HttpServer.servletMap.get(next);
            Class aClass = Class.forName(value);
            HttpServlet servlet = (HttpServlet) aClass.newInstance();
            servlet.service(new HttpServletRequest(resp.getRequest()), resp);
        }
    }

    public void include(HttpServletRequest req, HttpServletResponse resp) {
        request = req;
        response =resp;
    }
}
