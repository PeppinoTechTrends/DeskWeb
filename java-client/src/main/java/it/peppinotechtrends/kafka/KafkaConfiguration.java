package it.peppinotechtrends.kafka;

import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaConfiguration {
    //setup kafka producer
    public static Properties getProducerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "0.0.0.0:9092");
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", ByteArraySerializer.class.getName());
        props.put("acks", "1");
        props.put("batch.size", 16384);
        props.put("compression.type", "gzip");
        return props;
    }
}
