package MyWebTester;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class test {

    private static ArrayList<String> filesPath;
    private static int tempport;
    public static void main(String[] args) throws FileNotFoundException {
        /*String javaClassPath = System.getProperty("java.class.path");
        System.out.println(javaClassPath);
        System.setProperty("java.class.path", javaClassPath);
        javaClassPath = System.getProperty("java.class.path");
        System.out.println(javaClassPath);*/

        PrintStream ps = new PrintStream("testfile/all-s.txt");//创建一个打印输出流
        PrintStream out = System.out;//先把系统默认的打印输出流缓存
        System.setOut(ps);//把创建的打印输出流赋给系统。即系统下次向 ps输出
        out.print("请输入端口号(提示:本服务器为80)：");
        Scanner sc =new Scanner(System.in);
        tempport=sc.nextInt();
        String port = tempport+"";
        out.println("生成临时文件中……");
        System.out.print(GetAndPost.doGet("http://localhost:"+port+"/all.html"));

        ps= new PrintStream(("testfile/show3-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.doGet("http://localhost:"+port+"/show3.jsp"));

        ps= new PrintStream(("testfile/show5-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.doGet("http://localhost:"+port+"/show5.jsp"));

        ps= new PrintStream(("testfile/test1-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.SendPost("http://localhost:"+port+"/test1.html","legs=4&weight=15"));

        ps= new PrintStream(("testfile/test2-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.doGet("http://localhost:"+port+"/test2.jsp"));

        ps= new PrintStream(("testfile/test3-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.SendPost("http://localhost:"+port+"/test3.html","legs=4&weight=15"));

        ps= new PrintStream(("testfile/test4-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.doGet("http://localhost:"+port+"/test4.jsp"));

        ps= new PrintStream(("testfile/test6-s.txt"));
        System.setOut(ps);
        System.out.print(GetAndPost.SendPost("http://localhost:"+port+"/test6.jsp","username=admin&password=admin"));//这个也是服务器没有写
        out.println("文件生成完成，开始匹配……");

        filesPath =new ArrayList<String>();
        filesPath.add("testfile/all.txt");
        filesPath.add("testfile/all-s.txt");
        filesPath.add("testfile/show3.txt");
        filesPath.add("testfile/show3-s.txt");
        filesPath.add("testfile/show5.txt");
        filesPath.add("testfile/show5-s.txt");
        filesPath.add("testfile/test1.txt");
        filesPath.add("testfile/test1-s.txt");
        filesPath.add("testfile/test2.txt");
        filesPath.add("testfile/test2-s.txt");
        filesPath.add("testfile/test3.txt");
        filesPath.add("testfile/test3-s.txt");
        filesPath.add("testfile/test4.txt");
        filesPath.add("testfile/test4-s.txt");
        filesPath.add("testfile/test6.txt");
        filesPath.add("testfile/test6-s.txt");
        filesPath.add("testfile/all.txt");
        filesPath.add("testfile/all.txt");

        out.println("下面为两种服务器得到页面数据的相似度：");
        CodeFile file1 = new CodeFile("testfile/all.txt");
        CodeFile file2 = new CodeFile("testfile/all-s.txt");
        out.println("all.html:"+file1.compare(file2));

        file1 = new CodeFile("testfile/show3.txt");
        file2 = new CodeFile("testfile/show3-s.txt");
        out.println("show3.jsp:"+file1.compare(file2));

        file1 = new CodeFile("testfile/show5.txt");
        file2 = new CodeFile("testfile/show5-s.txt");
        out.println("show5.jsp:"+file1.compare(file2));

        file1 = new CodeFile("testfile/test1.txt");
        file2 = new CodeFile("testfile/test1-s.txt");
        out.println("test1.html:"+file1.compare(file2));

        file1 = new CodeFile("testfile/test2.txt");
        file2 = new CodeFile("testfile/test2-s.txt");
        out.println("test2.jsp:"+file1.compare(file2));

        file1 = new CodeFile("testfile/test3.txt");
        file2 = new CodeFile("testfile/test3-s.txt");
        out.println("test3.html:"+file1.compare(file2));

        file1 = new CodeFile("testfile/test4.txt");
        file2 = new CodeFile("testfile/test4-s.txt");
        out.println("test4.jsp:"+file1.compare(file2));

        file1 = new CodeFile("testfile/test6.txt");
        file2 = new CodeFile("testfile/test6-s.txt");
        out.println("test6.jsp:"+file1.compare(file2));

        out.println("匹配完成");

    }


}
