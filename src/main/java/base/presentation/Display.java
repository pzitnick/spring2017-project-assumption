package base.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends Canvas {

    private int width;
    private int height;
    private JFrame jFrame;
    private transient BufferedImage pixelMap;

    public Display(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        } else {
            this.width = width;
            this.height = height;
        }
    }

    public void initDisplay() {
        jFrame = new JFrame();
        jFrame.setTitle("Path Tracer");
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(this);

        pixelMap = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawImage(pixelMap, 0, 0, width, height, null);
    }

    public void setPixel(int x, int y, float r, float g, float b) {
        r = Math.min(255, r);
        g = Math.min(255, g);
        b = Math.min(255, b);

        pixelMap.setRGB(x, y, new Color((int) Math.floor(r), (int) Math.floor(g), (int) Math.floor(b)).getRGB());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 0) {
            throw new IllegalArgumentException();
        } else {
            this.width = width;
            if (jFrame != null) {
                jFrame.setSize(width, height);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height < 0) {
            throw new IllegalArgumentException();
        } else {
            this.height = height;
            if (jFrame != null) {
                jFrame.setSize(width, height);
            }
        }
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public BufferedImage getPixelMap() {
        return pixelMap;
    }

    public void setPixelMap(BufferedImage pixelMap) {
        this.pixelMap = pixelMap;
    }
}
