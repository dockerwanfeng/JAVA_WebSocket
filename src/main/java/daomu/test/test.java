package daomu.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class test {

    @GetMapping("/test")
    public String test(){
        System.out.println("test请求成功ok");
        return "index";
    }
}
