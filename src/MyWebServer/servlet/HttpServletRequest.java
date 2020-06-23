package mywebserver.servlet;

import mywebserver.http.HttpRequest;
import mywebserver.jsp.session;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class HttpServletRequest extends HttpRequest {
    session s = new session();
    HashMap<String,Object> attribute = new HashMap<String,Object>();

    public HttpServletRequest(HttpRequest request) throws IOException {
        super(request);
    }

    public HttpServletRequest(Socket sock) throws IOException {
        super(sock);
    }

    public String getParameter(String par) {
        if (header.contains(par + "=")) {
            int begin = header.indexOf(par + "=");
            int end = header.indexOf("&", begin);

            if (end == -1) {
                return header.substring(begin + par.length() + 1);
            } else {
                return header.substring(begin + par.length() + 1, end);
            }
        }
        return null;
    }

    public void setAttribute(String key, Object value) {
        attribute.put(key,value);
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return new RequestDispatcher(s);
    }

    public Object getAttribute(String key) {
        return attribute.get(key);
    }

    public session getSession() {
        return s;
    }
}
