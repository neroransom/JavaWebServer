package mywebserver.servlet;

import mywebserver.HttpServer;
import mywebserver.http.HttpRequest;
import mywebserver.http.HttpResponse;
import mywebserver.jsp.JspTranslation;

import javax.xml.crypto.OctetStreamData;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HttpServletResponse extends HttpResponse {
    private String contentType;

    public HttpServletResponse(HttpRequest req) throws IOException {
        super(req);
    }

    public PrintWriter getWriter() {
        return new PrintWriter(getOutputStream());
    }

    public void setContentType(String s) {
        contentType = s;
    }

    public void sendRedirect(String next) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException, ServletException {
        if(next.endsWith(".jsp"))
        {
            new JspTranslation().ofThe("/"+next);
            String value = "mywebserver.jsp.jspclass." + next.substring(0, next.indexOf(".jsp")) + "_jsp";
            Class aClass = Class.forName(value);
            HttpServlet servlet = (HttpServlet) aClass.newInstance();
            servlet.service(new HttpServletRequest(this.getRequest()),this);
        }
        else{
            String value = HttpServer.servletMap.get(next);
            Class aClass = Class.forName(value);
            HttpServlet servlet = (HttpServlet) aClass.newInstance();
            servlet.service(new HttpServletRequest(this.getRequest()),this);
        }
    }
}
