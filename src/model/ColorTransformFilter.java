package model;

/**
 * Color transform filter for color transformation.
 */
public class ColorTransformFilter implements Filter {

  double[][] matrix;

  /**
   * constructor for color transform filter.
   *
   * @param matrix matrix for filter
   */
  public ColorTransformFilter(double[][] matrix) {

    if (matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("matrix must be a 3x3 square");
    }

    this.matrix = matrix;
  }

  /**
   * Runs once per pixel in the image to be filtered.
   *
   * @param x         x coordinate of pixel location
   * @param y         y coordinate of pixel location
   * @param img       old image we are filtering
   * @param parameter optional parameter for some filters
   * @return IRGBPixel of filtered image
   */
  @Override
  public RGBPixel filterImage(int x, int y, Image img, int parameter) {

    int oldR = img.getPixel(x, y).red();
    int oldG = img.getPixel(x, y).green();
    int oldB = img.getPixel(x, y).blue();

    int r = (int) (matrix[0][0] * oldR + matrix[0][1] * oldG + matrix[0][2] * oldB);
    int g = (int) (matrix[1][0] * oldR + matrix[1][1] * oldG + matrix[1][2] * oldB);
    int b = (int) (matrix[2][0] * oldR + matrix[2][1] * oldG + matrix[2][2] * oldB);

    return new RGBPixelImpl(r, g, b);

  }
}
