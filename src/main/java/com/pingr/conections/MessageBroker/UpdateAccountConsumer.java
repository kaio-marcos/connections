package com.pingr.conections.MessageBroker;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

//perguntar depois
@Component  // prepara para injeção de dependência do @Autowired. antes era service, agora 'component', que, adota parte do comportamento
public class UpdateAccountConsumer {
    private final AccountService service;

    @Autowired
    public UpdateAccountConsumer(AccountService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "${topic.update-accounts}",
            groupId = "connection_update_accounts"
    )
    public void consume(String account) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignora propriedades não declaradas

        Account acc = mapper.readValue(account, Account.class);

        System.out.println("recebi update account json:");
        System.out.println(acc);
        this.service.updateAccount(acc);
    }
}
