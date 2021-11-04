package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTML Form을 통해 입력한 데이터를 변수에 저장
        // request.getParmeter() : String 타입으로 반환하기 때문에 숫자는 파싱해줘야함
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 비즈니스 로직 실행, member 객체를 생성하고 파라미터에 username, age 입력
        Member member = new Member(username, age);

        // memberRepository에 있는 store 객체에 member 객체를 저장하는 save() 메서드 실행
        // 시퀀스 변수가 +1 되고 그 값을 아이디에 할당한다. 그리고 username, age가 담긴 member 객체를 value로 추가
        memberRepository.save(member);

        // 성공했다는 메시지 출력력
        response.setContentType("text/html;");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
