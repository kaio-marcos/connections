package com.pingr.conections.Connections;

import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountRepository;
import com.pingr.conections.MessageBroker.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConnectionsService {
    private final AccountRepository repo;
    private final ProducerService producerService;

    @Autowired
    public ConnectionsService(AccountRepository repo, ProducerService producerService) {
        this.repo = repo;
        this.producerService = producerService;
    }


    public boolean stablishFriendshipBetween(ReceiveTwoIds ids) {
        if(ids.getIdA() == null || ids.getIdB() == null) throw new IllegalStateException("Os ids não podem ser nulos");

        if(ids.getIdA() == ids.getIdB()) throw new IllegalStateException("Os ids não podem ser iguais");

        Optional<Account> aOptional = this.repo.findById(ids.getIdA());
        Optional<Account> bOptional = this.repo.findById(ids.getIdB());

        if (!aOptional.isPresent() || !bOptional.isPresent()) return false;

        Account a = aOptional.get();
        Account b = bOptional.get();

        Set<Account> aFriends = a.getFriends();
        aFriends.add(b);
        a.setFriends(aFriends);

        Set<Account> bFriends = b.getFriends();
        bFriends.add(a);
        b.setFriends(bFriends);

        this.repo.saveAll(Arrays.asList(a,b));
        this.producerService.sendMessage(Arrays.asList(a,b));
        return true;
    }

    public Set<Account> viewAllFriends(Long id){
        Optional<Account> findAccount = this.repo.findById(id);
        if(!findAccount.isPresent())
            throw new IllegalStateException("Usuário não encontrado");

        Account account = findAccount.get();
        return account.getFriends();
    }

    public Boolean notFriends(ReceiveTwoIds ids){
        if(ids.getIdA() == null || ids.getIdB() == null) throw new IllegalStateException("Os ids não podem ser nulos");

        if(ids.getIdA() == ids.getIdB()) throw new IllegalStateException("Os ids não podem ser iguais");

        Optional<Account> aOptional = this.repo.findById(ids.getIdA());
        Optional<Account> bOptional = this.repo.findById(ids.getIdB());
        if(!aOptional.isPresent() || !bOptional.isPresent()) throw new IllegalStateException("Erro ao tentar encontrar account");
        Account a = aOptional.get();
        Account b = bOptional.get();

        //Set<> não tem o conceito de indíces
        Set<Account> newList = a.getFriends().stream().filter(account -> account.getId() != b.getId()).collect(Collectors.toSet());
        a.setFriends(newList);
        newList = b.getFriends().stream().filter(account -> account.getId() != a.getId()).collect(Collectors.toSet());
        b.setFriends(newList);

        this.repo.saveAll(Arrays.asList(a,b));
        return true;
    }

    public Integer countAllFriends(Long id){
        Optional<Account> findAccount = this.repo.findById(id);
        if(!findAccount.isPresent())
            throw new IllegalStateException("Usuário não encontrado");

        Account account = findAccount.get();
        return account.getFriends().size();
    }
}
