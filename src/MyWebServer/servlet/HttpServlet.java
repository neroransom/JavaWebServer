package mywebserver.servlet;

import java.io.IOException;

public class HttpServlet {

    public HttpServlet() {
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            doGet(request, response);
       } else if (request.getMethod().equals("POST")) {
            doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public void init() {
    }

    public void destroy() {
    }

}
