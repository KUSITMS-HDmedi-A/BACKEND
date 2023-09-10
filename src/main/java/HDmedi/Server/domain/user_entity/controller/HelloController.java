package HDmedi.Server.domain.user_entity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping
    public String helloController(){
        return "HELLO";
    }
}
