package MyWebServer;

import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer4 {
    public static void main(String a[]) throws Exception {
        final int httpd = 80;
        ServerSocket ssock = new ServerSocket(httpd);
        System.out.println("Listening the port 80 locally...");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("client has made socket connection");
            OneConnection_C client = new OneConnection_C(sock);
            new Thread(client).start();
        }
    }
}
