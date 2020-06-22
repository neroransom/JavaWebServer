package mywebserver.http;

import java.io.*;
import java.net.Socket;

public class HttpResponse {

    private Socket sock;
    private HttpRequest request;
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


    public void sendFile(String fname) throws IOException {
        fname = fname.substring(1);
        //String where = "web/site/" + fname; // create dir if necessary
        String where = "web/site/" + fname; // create dir if necessary
        if (where.contains(".. "))
            throw new SecurityException("No access to parent dirs");
        System.out.println("looking for " + where);
        File f = new File(where);
        if(f.exists()){
            DataInputStream din = new DataInputStream(new FileInputStream(f));
            int len = (int) f.length();
            byte[] buf = new byte[len];
            din.readFully(buf);
            out.writeBytes("HTTP/1.1 200 OK\r\n\r\n");
            out.write(buf);
            out.flush();
            out.close();
        }
        else{
            new ErrorSender(this).sendHtml404();
        }
    }

    public HttpRequest getRequest()
    {
        return request;
    }

    public DataOutputStream getOutputStream() {
        return out;
    }
}
