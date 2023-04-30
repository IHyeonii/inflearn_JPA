package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("hello") // hello URL로 오면 이 컨트롤러 호출될거야
  public String hello(Model model) {
    model.addAttribute("data", "hello~~~~"); //모델에 값을 담아서
    return "hello"; // 리턴은 화면 이름 -> templates 아래가 view name, hello.html 보여줄거야
  }
}
