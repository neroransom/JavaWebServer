package mywebserver.servlet;

import mywebserver.http.HttpRequest;

import java.io.IOException;
import java.net.Socket;

public class HttpServletRequest extends HttpRequest {
   //RequestDispatcher

    public HttpServletRequest(Socket sock) throws IOException {
        super(sock);
    }
}
