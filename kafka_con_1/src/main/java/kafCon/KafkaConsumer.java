package kafCon;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "topic-1", groupId = "your-consumer-group-id")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);
    }
}