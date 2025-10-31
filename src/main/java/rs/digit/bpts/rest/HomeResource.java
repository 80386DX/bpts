package rs.digit.bpts.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController (value = "/account")
public class HomeResource {

    @GetMapping("/")
    public void checkUser(){}
}
