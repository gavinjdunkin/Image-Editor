package model;

/**
 * Interface for representing the RGB value of an individual pixel.
 */
public interface RGBPixel {
  /** Red component.
   * @return red component of this pixel
   */
  int red();

  /** Green component.
   * @return green component of this pixel
   */
  int green();

  /** Blue component.
   * @return blue component of this pixel
   */
  int blue();
}
