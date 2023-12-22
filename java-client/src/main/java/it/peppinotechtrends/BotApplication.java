package it.peppinotechtrends;

import it.peppinotechtrends.frame.ScheduledFrameCapture;

public class BotApplication {
    private static void sentScreenToSite() throws InterruptedException {
        ScheduledFrameCapture.captureAndSendImages();
    }

    public static void main(String[] args) {
        try {
            sentScreenToSite();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
