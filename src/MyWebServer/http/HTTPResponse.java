package mywebserver.http;

import java.io.*;
import java.net.Socket;

public class HttpResponse {

    private HttpRequest request;
    private Socket sock;
    private DataOutputStream out = null;

    public HttpResponse(HttpRequest req) throws IOException {
        this.request = req;
        sock = request.sock;
        out = new DataOutputStream(sock.getOutputStream());
    }

    public void setRequest(HttpRequest request) throws IOException {
        this.request = request;
        sock = request.sock;
        out = new DataOutputStream(sock.getOutputStream());
    }


    public void sendFile(String fname) throws Exception {
        fname = fname.substring(1);
        String where = "/site/" + fname; // create dir if necessary
        if (where.indexOf(".. ") > -1)
            throw new SecurityException("No access to parent dirs");
        System.out.println("looking for" + where);
        File f = new File(where);
        DataInputStream din = new DataInputStream(new FileInputStream(f));
        int len = (int) f.length();
        byte[] buf = new byte[len];
        din.readFully(buf);
        out.writeBytes("HTTP/1.1 200 OK\r\n");
        out.writeBytes("Content-Length: " + len + "\r\n");
        out.writeBytes("Content-Type: text/html\r\n\n");
        out.write(buf);
        out.flush();
        out.close();
    }
}
