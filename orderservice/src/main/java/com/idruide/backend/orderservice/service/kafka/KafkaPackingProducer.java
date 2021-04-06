package com.idruide.backend.orderservice.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class KafkaPackingProducer {

    @Value("${idruide.kafka.packing.producer}")
    private  String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void publishToPacking(String message){

        this.kafkaTemplate.send(topicName,message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Order {}, packing {} failed to publish, because {}", message, ex.getMessage());
                // do something else, maybe inserting to log database, etc
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Order {}, packing  {} published successfuly", message);
            }
        });
        log.info("Just a dummy messsage for order {}, Packing {} published successfuly", message);
    }
}
