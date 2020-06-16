package MyWebServer;

import java.io. *;
import java.net. *;
class OneConnection_C extends OneConnection_B
        implements Runnable {
    OneConnection_C(Socket sock) throws Exception {
        super (sock);
    }

    public void run() {
        try {
            String filename = getRequest();
            sendFile(filename);
        } catch (Exception e) {
            System.out.println("Excpn: " + e);
        }
    }
}