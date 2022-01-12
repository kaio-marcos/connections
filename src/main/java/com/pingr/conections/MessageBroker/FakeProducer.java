package com.pingr.conections.MessageBroker;

import com.pingr.conections.Connections.AccountMirror.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FakeProducer {
    @Value(value = "${topic.accounts}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessage(Account account) {
        this.template.send(this.topic, account);
    }
}
