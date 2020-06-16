package MyWebServer;

import java.io. *;
import java.net. *;

class OneConnection {
    Socket sock;
    BufferedReader in= null;
    DataOutputStream out= null;

    OneConnection(Socket sock) throws Exception {
        this.sock = sock;
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new DataOutputStream(sock.getOutputStream());
    }

    String getRequest() throws Exception {
            String s = null;
            while ((s = in.readLine()) != null) {
                System.out.println("got: " + s);
            }
            return s;
        }
    }