package com.pingr.conections.MessageBroker;

import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component  // prepara para injeção de dependência do @Autowired. antes era service, agora 'component', que, adota parte do comportamento
public class NewAccountConsumer {
    private final AccountService service;

    @Autowired
    public NewAccountConsumer(AccountService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "${topic.accounts}",
            groupId = "connection_new_accounts"
    )
    public void consume(Account account) throws IOException {
        System.out.println("recebi account:");
        System.out.println(account);
        this.service.storeAccount(account);
    }
}
