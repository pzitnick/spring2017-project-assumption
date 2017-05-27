package base.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends Canvas {

  private int width;
  private int height;
  private JFrame jframe;
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
    jframe = new JFrame();
    jframe.setTitle("Path Tracer");
    jframe.setSize(width, height);
    jframe.setResizable(false);
    jframe.setLocationRelativeTo(null);
    jframe.add(this);
    jframe.setVisible(true);

    pixelMap = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);

    graphics.drawImage(pixelMap, 0, 0, width, height, null);
  }

  public void setPixel(int x, int y, float r, float g, float b) {
    pixelMap.setRGB(x, y, new Color((int) r, (int) g, (int) b).getRGB());
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    if (width < 0) {
      throw new IllegalArgumentException();
    } else {
      this.width = width;
      if (jframe != null) {
        jframe.setSize(width, height);
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
      if (jframe != null) {
        jframe.setSize(width, height);
      }
    }
  }

  public JFrame getjframe() {
    return jframe;
  }

  public void setjframe(JFrame jFrame) {
    this.jframe = jframe;
  }

  public BufferedImage getPixelMap() {
    return pixelMap;
  }

  public void setPixelMap(BufferedImage pixelMap) {
    this.pixelMap = pixelMap;
  }
}
