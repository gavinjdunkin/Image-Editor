package model;

import java.io.IOException;

/**
 * Image interface for storing an image.
 */
public interface Image {

  /** Save to a file.
   * @param filename filename to save to
   * @throws IOException when there is a file access error
   */
  void save(String filename) throws IOException;

  /**
   * Gets the color of a pixel at (x, y).
   *
   * @param x x location
   * @param y y location
   * @return src.IRGBPixel color
   */
  RGBPixel getPixel(int x, int y);

  /**
   * Gets the width in pixels of this image.
   *
   * @return width in pixels
   */
  int getWidth();

  /**
   * Gets the height in pixels of this image.
   *
   * @return height in pixels
   */
  int getHeight();
}
