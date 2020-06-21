package mywebserver.servlet;

import mywebserver.http.HttpRequest;
import java.io.IOException;
import java.net.Socket;

public class HttpServletRequest extends HttpRequest {
   //RequestDispatcher
    public HttpServletRequest(HttpRequest request) throws IOException {
        super(request);
    }

    public HttpServletRequest(Socket sock) throws IOException {
        super(sock);
    }

    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }
}
