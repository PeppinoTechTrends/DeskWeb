package it.peppinotechtrends.frame;


import it.peppinotechtrends.kafka.KafkaConfiguration;
import it.peppinotechtrends.kafka.KafkaImageSender;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledFrameCapture {
    //send all frame to kafka, which elaborate in a video
    public static void captureAndSendImages() throws InterruptedException {
        Properties props = KafkaConfiguration.getProducerProperties();
        try (KafkaProducer<String, byte[]> producer = new KafkaProducer<>(props)) {
            String topic = "streamingTopic";

            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
                try {
                        byte[] imageBytes;
                        try {
                            BufferedImage image = ScreenCapture.captureScreen();
                            imageBytes = ImageCompressor.compressImage(image);
                            KafkaImageSender.sendToKafka(producer, topic, imageBytes);
                        } catch (IOException | AWTException e) {
                            throw new RuntimeException(e);
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 0, 50, TimeUnit.MILLISECONDS);

            Thread.currentThread().join();
        }
    }
}