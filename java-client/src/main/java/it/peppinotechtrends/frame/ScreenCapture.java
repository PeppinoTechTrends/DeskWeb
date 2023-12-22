package it.peppinotechtrends.frame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenCapture {
    //Get a Screenshot, a frame of a streaming
    public static BufferedImage captureScreen() throws AWTException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        Robot robot = new Robot();
        return robot.createScreenCapture(screenRect);
    }
}