package app.messages;

import javax.servlet.annotation.WebListener;

import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final String helloMsg = "Hello Spring Boot!";

    // @GetMapping("/")
    // public String home(Model model) {
    //     model.addAttribute("welcomeMsg", helloMsg);
    //     return "home";
    // }
    
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        System.out.println("directing as ModelAndView.....");
        mv.addObject("welcomeMsg", helloMsg );
        return mv;
    }
}
