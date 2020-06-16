package MyWebServer;

import java.net.Socket;

class OneConnection_A extends OneConnection {
    OneConnection_A(Socket sock) throws Exception {
        super(sock);
    }

    String getRequest() throws Exception {
        String s = null;
        while ((s = in.readLine()) != null) {
            System.out.println("got: " + s);
            if (s.indexOf("GET") > -1) {
                s = s.substring(5);
                int i = s.indexOf(" ");
                System.out.println("file: " + s.substring(0, i));
                return s.substring(0, i);
            }
        }
        return null;
    }
}
