package it.peppinotechtrends.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaImageSender {
    //send a message to kafka
    public static void sendToKafka(KafkaProducer<String, byte[]> producer, String topic, byte[] imageBytes) {

        producer.send(new ProducerRecord<>(topic, imageBytes), (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                System.out.println("Sent message with offset: " + metadata.offset());
            }
        });
    }
}
