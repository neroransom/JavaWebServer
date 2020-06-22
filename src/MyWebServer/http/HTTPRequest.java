package mywebserver.http;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class HttpRequest{
    //private BufferedReader in = null;
    private InputStream in;
    String header = "";
    private String URI = "";
    private String method;
    Socket sock;

    public HttpRequest(Socket sock) throws IOException {
        this.sock = sock;
        in = sock.getInputStream();
        //in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        init();
    }

    public HttpRequest(HttpRequest copy) throws IOException {
        this.in = copy.in;
        this.header = copy.header;
        this.method = copy.method;
        this.sock = copy.sock;
    }


    public String init() throws IOException {
//        String s = null;
//        while ((s = in.readLine()) != null)  {
//            System.out.println("got: " + s);
//            header+=s;
//        }
        StringBuilder sb = new StringBuilder(1024);
        byte[] buffer = new byte[1024];

        int len = in.read(buffer);

        for (int i = 0; i < len; ++i) {
            sb.append((char)buffer[i]);
        }

        header = sb.toString();

        System.out.println("GOT:\r\n"+header);

        if(header.isEmpty())
        {
            return null;
        }
        else
        {
            String[] temp = header.split(" |\r\n");
            method = temp[0];
            URI = temp[1];
            if(URI.endsWith("/")) URI = URI.substring(0, URI.lastIndexOf("/"));
            return URI;
        }
    }

    public String getUrl() {
        return URI;
    }

    public String getMethod() {
        return method;
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

    public void setAttribute(String pet, String pet1) {
    }
}
