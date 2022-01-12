package com.pingr.conections.Connections.AccountMirror;

import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountRepository;
import com.pingr.conections.MessageBroker.FakeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {
    private final AccountRepository repo;

    @Autowired
    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public void storeAccount(Account account) {
        this.repo.save(account);
        System.out.println("salvei a conta:");
        System.out.println(account);
    }

    public void updateAccount(Account account){
        Optional<Account> accountFind = repo.findById(account.getId());
        if(!accountFind.isPresent()) {
            storeAccount(account);
        }
        else{
            Account accountUpdate = accountFind.get();
            if(account.getUsername() != null)
                accountUpdate.setUsername(account.getUsername());
            repo.save(accountUpdate);
        }
    }

    public void deleteAccount(Account account){
        Optional<Account> accountFind = repo.findById(account.getId());
        if(accountFind.isPresent()) {
            Account accountFound = accountFind.get();
            repo.delete(accountFound);
        }
    }
}
