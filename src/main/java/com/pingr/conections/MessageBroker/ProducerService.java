package com.pingr.conections.MessageBroker;

import com.pingr.conections.Connections.AccountMirror.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    @Value(value = "${topic.new-following}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, List<Object>> template;

    public void sendMessage(List<Object> accounts) {
        this.template.send(this.topic, accounts);
    }
}
