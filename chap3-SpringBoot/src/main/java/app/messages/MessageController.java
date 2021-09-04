package app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        String helloMessage = "Hello, Spring Boot!";
        model.addAttribute("message", helloMessage);
        return "welcome";
    }
    /**
     * MVC 에서 모델과 뷰를 사용하는 또 다른 방법은 ModelAndView 를 이용하는 것이다.
     * 핸들러에서 org.springframework.web.servlet.ModelAndView 인스턴스를 반환하면 된다.
     * 
     * Model 객체를 핸들러 메소드의 파라미터로 등록할 필요가 없다.
     * 대신 템플릿 파일(html)의 이름을 전달해서 ModelAndView 인스턴스를 생성하고 addObject() 메소드를 통해 데이터를 추가한다.
     * 위에랑 작동하는 것은 똑같다.
     */
    @GetMapping("/mvTest")
    public ModelAndView mvTest() {
        String helloMessage = "Hello, Spring Boot!";
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("message", helloMessage);
        return mv;
    }

    
}
