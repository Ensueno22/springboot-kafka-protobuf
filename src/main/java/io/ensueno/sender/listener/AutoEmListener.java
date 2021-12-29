package io.ensueno.sender.listener;

import io.ensueno.sender.model.ReqSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutoEmListener {

    @KafkaListener(topics="${spring.kafka.topics.em-topic}")
    public void onMessage(ConsumerRecord<String, byte[]> record){
        try {
            ReqSendMessage.ReqEmMessage reqMessage = ReqSendMessage.ReqEmMessage.parseFrom(record.value());
            log.info("message={}", reqMessage.getReqMessage());
            Thread.sleep(500);
        } catch (Exception e){
            log.error("exception", e);
        }
    }

}
