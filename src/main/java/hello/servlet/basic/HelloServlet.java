package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("helloServlet.service");

        System.out.println("request = " + request); // request = org.apache.catalina.connector.RequestFacade@6fbb9bb8
        System.out.println("response = " + response); // response = org.apache.catalina.connector.ResponseFacade@24e6dfd7
        // HttpServletRequest, Response : 톰캣 프레임워크에 있는 인터페이스이다.

        System.out.println("username = " + request.getParameter("username"));

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("hello " + request.getParameter("username"));
    }
}
