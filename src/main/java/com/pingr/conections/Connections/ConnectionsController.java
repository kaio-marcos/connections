package com.pingr.conections.Connections;

import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.MessageBroker.FakeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/connections")
public class ConnectionsController {
    private final FakeProducer fakeProducer;
    private final ConnectionsService connectionsService;

    @Autowired
    public ConnectionsController(FakeProducer fakeProducer, ConnectionsService connectionsService) {
        this.fakeProducer = fakeProducer;
        this.connectionsService = connectionsService;
    }

    // salvou a conta automáticamente
    // já ouvi dizer que o jpa salvava automáticamente sem precisar executar um save no repository
    @PostMapping(path = "/test")
    public Account fakeCreate() {
        Account fake = new Account(12L, "fake username", new HashSet<>());
        this.fakeProducer.sendMessage(fake);
        return fake;
    }

    @PostMapping
    public boolean addFriends(@RequestBody ReceiveTwoIds ids) {
        return this.connectionsService.stablishFriendshipBetween(ids);
    }

    @GetMapping
    public Set<Account> viewAllFriends(@RequestParam(value = "id") Long id){
        return this.connectionsService.viewAllFriends(id);
    }

    @DeleteMapping
    public Boolean notFriends(@RequestBody ReceiveTwoIds ids){
        return this.connectionsService.notFriends(ids);
    }

    @GetMapping(path = "/count")
    public Integer countAllFriends(@RequestParam(value = "id") Long id){
        return this.connectionsService.countAllFriends(id);
    }

}
