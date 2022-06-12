package model;

/**
 * Implementation of IRGBPixel, stores each component as a separate integer.
 */
public class RGBPixelImpl implements RGBPixel {
  private final int r;
  private final int g;
  private final int b;
  //INVARIANT: all components are between 0 and 255 inclusive

  /**
   * Construct pixel from red, green, and blue values [0-255].
   * Clamps value to [0-255].
   *
   * @param r red
   * @param g green
   * @param b blue
   */
  public RGBPixelImpl(int r, int g, int b) {

    this.r = Math.max(0, Math.min(r, 255));
    this.g = Math.max(0, Math.min(g, 255));
    this.b = Math.max(0, Math.min(b, 255));
  }

  /**
   * Construct a grey pixel with intensity [0-255].
   *
   * @param i intensity
   */
  public RGBPixelImpl(int i) {

    i = Math.max(0, Math.min(i, 255));

    this.r = i;
    this.g = i;
    this.b = i;
  }

  /**
   * copy constructor.
   *
   * @param other pixel to copy
   */
  public RGBPixelImpl(RGBPixel other) {
    this.r = other.red();
    this.g = other.green();
    this.b = other.blue();
  }

  /**
   * Red component.
   *
   * @return red component of this pixel [0-255]
   */
  @Override
  public int red() {
    return r;
  }

  /**
   * Green component.
   *
   * @return green component of this pixel [0-255]
   */
  @Override
  public int green() {
    return g;
  }

  /**
   * Blue component.
   *
   * @return blue component of this pixel [0-255]
   */
  @Override
  public int blue() {
    return b;
  }
}
