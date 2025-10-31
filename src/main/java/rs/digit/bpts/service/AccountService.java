package rs.digit.bpts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.digit.bpts.domain.Account;
import rs.digit.bpts.repos.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> findAll(){
      return repo.findAll();
    }

}
