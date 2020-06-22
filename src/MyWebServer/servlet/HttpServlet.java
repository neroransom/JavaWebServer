package mywebserver.servlet;

import mywebserver.http.ErrorSender;

import java.io.IOException;

public class HttpServlet {

    public HttpServlet() {
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        if (request.getMethod().equals("GET")) {
            doGet(request, response);
       } else if (request.getMethod().equals("POST")) {
            doPost(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        new ErrorSender(response).sendHtml404Servlet();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        new ErrorSender(response).sendHtml404Servlet();
    }

    public void init() {
    }

    public void destroy() {
    }

}
