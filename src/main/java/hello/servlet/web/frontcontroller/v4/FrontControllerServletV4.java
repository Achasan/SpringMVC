package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// V4 : 개발자의 입장에서 좀 더 실용적으로 구조를 바꿈, ModelView 객체를 사용하지 않고, 바로 model과 논리이름을 리턴하도록 설계
@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllers = new HashMap<>();

    public FrontControllerServletV4() {
        controllers.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllers.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllers.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);

        ControllerV4 controller = controllers.get(uri);
        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = requestToMap(request); // request 객체에 있는 값을 paramMap으로 보냄
        Map<String, Object> model = new HashMap<>();          // ModelView 객체 대신 model 객체를 바로 생성함
        String viewName = controller.process(paramMap, model); // process는 논리이름을 ModelView에 넣지않고 바로 반환한다.

        MyView myView = modelResolver(viewName);
        myView.render(model, request, response);
    }

    private Map<String, String> requestToMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView modelResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
