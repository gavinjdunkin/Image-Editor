package model;

/**
 * Function object to filter images.
 */
public interface Filter {
  /** Runs once per pixel in the image to be filtered.
   * @param x x coordinate of pixel location
   * @param y y coordinate of pixel location
   * @param img old image we are filtering
   * @param parameter optional parameter for some filters
   * @return IRGBPixel of filtered image
   */
  RGBPixel filterImage(int x, int y, Image img, int parameter);
}
