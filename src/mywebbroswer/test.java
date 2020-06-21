package mywebbroswer;

public class test {

    public static void main(String[] args){
        //System.out.print(GetAndPost.doGet("http://localhost/test2.jsp"));
        //System.out.print(GetAndPost.SendPost("http://localhost/test3.html","legs=4&weight=15"));
        //System.out.print(GetAndPost.doGet("http://localhost:8080/WebContent/test1.html"));
        //System.out.print(GetAndPost.SendPost("http://localhost/test3.html","legs=4&weight=50"));
        //System.out.print(GetAndPost.SendPost("http://localhost:8080/WebContent/test1.html","legs=4&weight=51"));
        //System.out.print(GetAndPost.doGet("http://localhost/test6.jsp"));
        //System.out.print(GetAndPost.SendPost("http://localhost/test6.jsp","username=admin&password=admin"));
        System.out.print(GetAndPost.SendPost("http://localhost/test1.html","legs=8&weight=15"));
        System.out.print(GetAndPost.SendPost("http://localhost:8081/test1.html","legs=8&weight=15"));
    }
}
