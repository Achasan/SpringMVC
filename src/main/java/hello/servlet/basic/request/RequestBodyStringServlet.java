package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 메시지바디를 request 객체에서 받아오는 메서드 getInputStream().
        // InputStream으로 반환하기 때문에 해당 객체로 받아주어야한다. inputStream은 byte코드로 되어있어서 따로 인코딩을 해주어야한다.
        ServletInputStream inputStream = request.getInputStream();

        // StreamUtils : Spring에서 제공하는 Stream인코딩 객체,
        // byte로 되어있는 HTTP 메시지가 들어있는 inputStream 객체를 UTF-8로 인코딩한다. copyToString 사용
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println(messageBody);

        response.getWriter().write("ok");
    }
}
