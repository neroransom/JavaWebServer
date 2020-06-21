package mywebserver.servlet;

import mywebserver.http.HttpRequest;
import mywebserver.http.HttpResponse;

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

}
