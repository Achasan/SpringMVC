package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v1 밑으로 오는 모든 URI는 해당 서블릿을 거치도록 설정한다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // 어떤 URI이냐에 따라서 호출할 객체정보가 담긴 hashMap 객체 생성
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // 해당 서블릿이 호출되면 Map 객체에 URI을 key로, URI 별로 호출될 Controller들을 value로 집어넣도록 생성자 작성
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);

        // 모든 ControllerV1 객체는 ControllerV1 인터페잇를 상속한다.
        ControllerV1 controller = controllerMap.get(uri);
        if(controller == null) {
            // 페이지를 찾을 수 없다는 메시지 출력
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Override 되어있는 Process 호출
        controller.process(request, response);
    }
}
