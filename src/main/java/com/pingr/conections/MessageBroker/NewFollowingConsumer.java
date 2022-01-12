package com.pingr.conections.MessageBroker;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingr.conections.Connections.AccountMirror.Account;
import com.pingr.conections.Connections.AccountMirror.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//perguntar depois
@Component  // prepara para injeção de dependência do @Autowired. antes era service, agora 'component', que, adota parte do comportamento
public class NewFollowingConsumer {
    private final AccountService service;

    @Autowired
    public NewFollowingConsumer(AccountService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "${topic.new-following}",
            groupId = "connection_new-following_accounts"
    )
    public void consume(String account) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignora propriedades não declaradas

        List<Account> acc = mapper.readValue(account, List.class);

        System.out.println("contas se seguiram:");
        System.out.println(acc);
    }
}
