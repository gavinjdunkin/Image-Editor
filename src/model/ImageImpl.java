package model;

import java.io.IOException;

/**
 * Image implementation as a 2d array of IRGBPixels.
 */
public class ImageImpl implements Image {
  RGBPixel[][] pixels;

  /**
   * Load image from PPM file.
   *
   * @param filename filename of ppm file
   */
  public ImageImpl(String filename) {
    pixels = ImageUtil.loadImage(filename);
  } 

  /**
   * Create a new image by applying a filter to another image.
   *
   * @param other     image to filter
   * @param filter    filter to apply
   * @param parameter filter parameter
   */
  public ImageImpl(Image other, Filter filter, int parameter) {
    pixels = new RGBPixel[other.getWidth()][other.getHeight()];

    for (int x = 0; x < pixels.length; x++) {
      for (int y = 0; y < pixels[0].length; y++) {
        pixels[x][y] = filter.filterImage(x, y, other, parameter);
      }
    }
  }


  /**
   * Save to a ppm file.
   *
   * @param filename filename to save to
   * @throws IOException when there is a file access error
   */
  @Override
  public void save(String filename) throws IOException {
    ImageUtil.saveImage(filename, pixels);
  }

  /**
   * Gets the color of a pixel at (x, y).
   *
   * @param x x location
   * @param y y location
   * @return src.IRGBPixel color
   */
  @Override
  public RGBPixel getPixel(int x, int y) throws IllegalArgumentException {

    if (x < 0 || x > this.getWidth() - 1) {
      throw new IllegalArgumentException();
    }

    if (y < 0 || y > this.getHeight() - 1) {
      throw new IllegalArgumentException();
    }

    return pixels[x][y];
  }

  /**
   * Gets the width in pixels of this image.
   *
   * @return width in pixels
   */
  @Override
  public int getWidth() {
    return pixels.length;
  }

  /**
   * Gets the height in pixels of this image.
   *
   * @return height in pixels
   */
  @Override
  public int getHeight() {
    return pixels[0].length;
  }
}
