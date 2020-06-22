package mywebserver.sender;

import mywebserver.http.HttpResponse;

public class HttpSender implements Runnable {
    HttpResponse response;

    public HttpSender(HttpResponse resp){
        this.response = resp;
    }

    @Override
    public void run() {
        try {
            if(response.getRequest().getUrl().equals("")){
                response.sendFile("/all.html");
            }
            else {
                response.sendFile((response.getRequest().getUrl()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
