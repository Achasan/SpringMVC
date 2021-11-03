package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        // response.setHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello"); // 임의의 헤더를 만드는 것도 가능하다.


        // [Header 편의 메서드]
        content(response);
        // [Cookie 메서드]
        cookie(response);
        // [Redirect 메서드]
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("OK");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        // response.Content-length(2) : 작성하지 않으면 자동계산해서 출력한다. 필수값이다. println은 엔터가 들어가므로 3으로 나온다.
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600
        //response.setHeader("set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code: 302
        //Location: /basic/hello-form.html

        // response.setStatus(HttpServletResponse.SC_FOUND); //302
        // response.setHeader("Location", "basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html"); // 상태코드 302, redirect
    }
}
