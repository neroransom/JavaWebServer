package MyWebServer;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String a[]) throws Exception {
        final int httpd = 80;
        ServerSocket ssock = null;
        try {
            ssock = new ServerSocket(httpd);
            System.out.println("have opened port 80 locally");
            Socket sock = ssock.accept();
            System.out.println("client has made socket connection");
            OneConnection_B client = new OneConnection_B(sock);
            String s = client.getRequest();
            client.sendFile("ex.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
