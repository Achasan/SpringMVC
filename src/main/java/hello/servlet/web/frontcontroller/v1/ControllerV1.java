package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 여러 컨트롤러들을 만들기 위해서 인터페이스를 만듦
// FrontController에서 매핑정보를 찾은 다음 일관성있게 설계된 Controller를 호출하기위해 인터페이스를 사용
// (로직의 일관성을 가져갈 수 있다.)
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
