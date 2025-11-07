package rs.digit.bpts.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.service.AccountService;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts")
public class AccountRest {

    private final Logger logger = LoggerFactory.getLogger(AccountRest.class);

    @Autowired
    private AccountService service;

    @GetMapping("/all")
    public List<Account> findAll(){

        return service.findAll();
    }

}
