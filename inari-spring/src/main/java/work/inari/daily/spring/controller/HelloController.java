package work.inari.daily.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@RequestMapping("inari/api")
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "hello, world!";
    }
}
