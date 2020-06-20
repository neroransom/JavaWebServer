package mywebserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class HttpRequest{
    private BufferedReader in = null;
    ArrayList<String> header = null;
    private String URI = null;
    private String method;
    Socket sock;

    public HttpRequest(Socket sock) throws IOException {
        this.sock = sock;
        header = new ArrayList<String>();
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }


    public String getRequest() throws Exception {
        String s = null;
        while ((s = in.readLine()) != null&&!s.equals(""))  {
            System.out.println("got: " + s);
            header.add(s);
        }
        if(header.isEmpty())
        {
            return null;
        }
        else
        {
            String command = header.get(0);
            String[] buff = command.split(" |\r\n");
            method = buff[0];
            URI = buff[1];
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
}
