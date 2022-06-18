package model;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and save colors to pixel array.
   *
   * @param filename the path of the file.
   */
  public static RGBPixel[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    RGBPixel[][] pixels = new RGBPixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
        pixels[j][i] = new RGBPixelImpl(r, g, b);
      }
    }

    return pixels;
  }

  /**
   * saves the ppm.
   *
   * @param filename name of file
   * @param pixels   pixel 2d array
   * @throws IOException error if cant save
   */
  public static void savePPM(String filename, RGBPixel[][] pixels) throws IOException {
    FileWriter out = new FileWriter(filename);

    out.append(String.format("P3 %d %d 255\n", pixels.length, pixels[0].length));

    for (int y = 0; y < pixels[0].length; y++) {
      for (int x = 0; x < pixels.length; x++) {
        RGBPixel pixel = pixels[x][y];
        out.append(String.format("%d %d %d\n", pixel.red(), pixel.green(), pixel.blue()));
      }
    }

    out.close();
  }

  public static RGBPixel[][] loadImage(String filename) {
    if (filename.endsWith(".ppm")) {
      return readPPM(filename);
    }

    BufferedImage image;

    try {
      image = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("file not found");
    }

    RGBPixel[][] pixels = new RGBPixel[image.getWidth()][image.getHeight()];
    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[0].length; y++) {
        int color = image.getRGB(x, y);
        int r = ColorModel.getRGBdefault().getRed(color);
        int g = ColorModel.getRGBdefault().getGreen(color);
        int b = ColorModel.getRGBdefault().getBlue(color);
        pixels[x][y] = new RGBPixelImpl(r, g, b);
      }
    }

    return pixels;
  }

  public static void saveImage(String filename, RGBPixel[][] pixels) throws IOException {
    if (filename.endsWith(".ppm")) {
      savePPM(filename, pixels);
      return;
    }

    BufferedImage image = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        image.setRGB(x, y, (pixels[x][y].red() << 16) + (pixels[x][y].green() << 8) + (pixels[x][y].blue() << 0));

      }
    }

    if (!filename.contains(".")) {
      throw new IllegalArgumentException("Filename must contain a file extension");
    }


    boolean status = ImageIO.write(image, filename.substring(filename.lastIndexOf(".") + 1), new File(filename));

    if (!status) {
      throw new IllegalArgumentException("invalid file extension");
    }

  }

  /**
   * demo main method.
   *
   * @param args args
   */
  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    //src.ImageUtil.readPPM(filename);
  }
}

