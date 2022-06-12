package model;

import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Tests the RGB pixel implementation.
 */
public class RGBPixelImplTest {
  RGBPixel p0;
  RGBPixel p1;
  RGBPixel p2;
  RGBPixel p3;

  @Test
  public void testConstructors() {
    try {
      p0 = new RGBPixelImpl(0);
      assertEquals(0, p0.red());
      assertEquals(0, p0.green());
      assertEquals(0, p0.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p1 = new RGBPixelImpl(128, 0, 0);
      assertEquals(128, p1.red());
      assertEquals(0, p1.green());
      assertEquals(0, p1.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p2 = new RGBPixelImpl(0, 255, 255);
      assertEquals(0, p2.red());
      assertEquals(255, p2.green());
      assertEquals(255, p2.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p3 = new RGBPixelImpl(p2);
      assertEquals(0, p3.red());
      assertEquals(255, p3.green());
      assertEquals(255, p3.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }

    //invalid testing
    try {
      RGBPixel p4 = new RGBPixelImpl(-1);
      fail();
    }
    catch (IllegalArgumentException e) {
      //success
    }
    try {
      RGBPixel p5 = new RGBPixelImpl(-1, -1, 300);
      fail();
    }
    catch (IllegalArgumentException e) {
      //success
    }
    try {
      RGBPixel p6 = new RGBPixelImpl(300);
      fail();
    }
    catch (IllegalArgumentException e) {
      //success
    }

  }

  @Test
  public void testGetters() {

    try {
      p0 = new RGBPixelImpl(0);
      assertEquals(0, p0.red());
      assertEquals(0, p0.green());
      assertEquals(0, p0.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p1 = new RGBPixelImpl(128, 0, 0);
      assertEquals(128, p1.red());
      assertEquals(0, p1.green());
      assertEquals(0, p1.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p2 = new RGBPixelImpl(0, 255, 255);
      assertEquals(0, p2.red());
      assertEquals(255, p2.green());
      assertEquals(255, p2.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      p3 = new RGBPixelImpl(p2);
      assertEquals(0, p3.red());
      assertEquals(255, p3.green());
      assertEquals(255, p3.blue());
    } catch (IllegalArgumentException e) {
      fail();
    }

  }
}