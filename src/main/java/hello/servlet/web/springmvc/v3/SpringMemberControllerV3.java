package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);

        return "members";
    }

    // GET방식으로만 데이터를 받아올 수 있도록 설정, 다른 방식으로 요청하면 405에러로 응답한다.(Method not allowed)
    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    // Model : 스프링에서 제공하는 Model 객체이다. 기존에 사용하던 ModelView 객체에서 Model 부분(Map)만 제공한다고 생각하면 된다.
    // return 할 떄는 로직기능을 리턴하고, application.properties에서 설정했던 prefix와 suffix가 합쳐져서 URI가 생성된다.
    @PostMapping("/save")
    public String save(@RequestParam("username") String username,
                             @RequestParam("age") int age,
                             Model model){

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
