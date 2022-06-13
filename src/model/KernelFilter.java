package model;

/**
 * Filter that applies a blur/sharpen kernel to each pixel.
 */
public class KernelFilter implements Filter {
  double[][] kernel;

  public KernelFilter(double[][] kernel) {
    this.kernel = kernel;
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
    double r = 0;
    double g = 0;
    double b = 0;
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        int kx = x - (kernel.length / 2) + i;
        int ky = y - (kernel[0].length / 2) + j;
        if (kx >= 0 && kx < img.getWidth() && ky >= 0 && ky < img.getHeight()) {
          RGBPixel p = img.getPixel(kx, ky);
          r += p.red() * kernel[i][j];
          g += p.green() * kernel[i][j];
          b += p.blue() * kernel[i][j];
        }
      }
    }

    return new RGBPixelImpl((int) r, (int) g, (int) b);
  }
}
