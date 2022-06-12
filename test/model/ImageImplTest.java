package model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the image implementation.
 */
public class ImageImplTest {

  @Test
  public void testConstructor() {
    try {
      Image i0 = new ImageImpl("koala.ppm");
      assertEquals(101, i0.getPixel(0, 0).red());
      assertEquals(90, i0.getPixel(0, 0).green());
      assertEquals(58, i0.getPixel(0, 0).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i0 = new ImageImpl("koala.ppm");
      Image i1 = new ImageImpl(i0,
              (x, y, img, p) ->
                      new RGBPixelImpl(img.getPixel(x, (img.getHeight() - 1) - y)), 0);
      assertEquals(218, i1.getPixel(0, 0).red());
      assertEquals(199, i1.getPixel(0, 0).green());
      assertEquals(185, i1.getPixel(0, 0).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i0 = new ImageImpl("koala.ppm");
      Image i1 = new ImageImpl(i0,
              (x, y, img, p) ->
                      new RGBPixelImpl(img.getPixel(x, (img.getHeight() - 1) - y)), 0);
      Image i2 = new ImageImpl(i1, (x, y, img, p) ->
              new RGBPixelImpl(img.getPixel(x, y).red()), 0);
      assertEquals(218, i2.getPixel(0, 0).red());
      assertEquals(218, i2.getPixel(0, 0).green());
      assertEquals(218, i2.getPixel(0, 0).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      Image i3 = new ImageImpl("sample.ppm");
      assertEquals(0, i3.getPixel(0, 0).red());
      assertEquals(255, i3.getPixel(0, 0).green());
      assertEquals(255, i3.getPixel(0, 0).blue());

      assertEquals(255, i3.getPixel(1, 0).red());
      assertEquals(0, i3.getPixel(1, 0).green());
      assertEquals(255, i3.getPixel(1, 0).blue());

      assertEquals(255, i3.getPixel(0, 1).red());
      assertEquals(0, i3.getPixel(0, 1).green());
      assertEquals(255, i3.getPixel(0, 1).blue());

      assertEquals(255, i3.getPixel(1, 1).red());
      assertEquals(255, i3.getPixel(1, 1).green());
      assertEquals(255, i3.getPixel(1, 1).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testSave() {
    try {
      Image i0 = new ImageImpl("sample.ppm");
      Image i1 = new ImageImpl(i0, (x, y, img, p) ->
              new RGBPixelImpl(img.getPixel(x, y).red()), 0);
      try {
        i0.save("sample-copy.ppm");
        i1.save("sample-red.ppm");

        Image i2 = new ImageImpl("sample-copy.ppm");
        Image i3 = new ImageImpl("sample-red.ppm");

        assertEquals(i0.getPixel(0, 0).red(), i2.getPixel(0, 0).red());
        assertEquals(i0.getPixel(0, 0).green(), i2.getPixel(0, 0).green());
        assertEquals(i0.getPixel(0, 0).blue(), i2.getPixel(0, 0).blue());

        assertEquals(i0.getPixel(1, 0).red(), i2.getPixel(1, 0).red());
        assertEquals(i0.getPixel(1, 0).green(), i2.getPixel(1, 0).green());
        assertEquals(i0.getPixel(1, 0).blue(), i2.getPixel(1, 0).blue());

        assertEquals(i0.getPixel(0, 1).red(), i2.getPixel(0, 1).red());
        assertEquals(i0.getPixel(0, 1).green(), i2.getPixel(0, 1).green());
        assertEquals(i0.getPixel(0, 1).blue(), i2.getPixel(0, 1).blue());

        assertEquals(i0.getPixel(1, 1).red(), i2.getPixel(1, 1).red());
        assertEquals(i0.getPixel(1, 1).green(), i2.getPixel(1, 1).green());
        assertEquals(i0.getPixel(1, 1).blue(), i2.getPixel(1, 1).blue());

        assertEquals(i1.getPixel(0, 0).red(), i3.getPixel(0, 0).red());
        assertEquals(i1.getPixel(0, 0).green(), i3.getPixel(0, 0).green());
        assertEquals(i1.getPixel(0, 0).blue(), i3.getPixel(0, 0).blue());

        assertEquals(i1.getPixel(1, 0).red(), i3.getPixel(1, 0).red());
        assertEquals(i1.getPixel(1, 0).green(), i3.getPixel(1, 0).green());
        assertEquals(i1.getPixel(1, 0).blue(), i3.getPixel(1, 0).blue());

        assertEquals(i1.getPixel(0, 1).red(), i3.getPixel(0, 1).red());
        assertEquals(i1.getPixel(0, 1).green(), i3.getPixel(0, 1).green());
        assertEquals(i1.getPixel(0, 1).blue(), i3.getPixel(0, 1).blue());

        assertEquals(i1.getPixel(1, 1).blue(), i3.getPixel(1, 1).blue());
        assertEquals(i1.getPixel(1, 1).green(), i3.getPixel(1, 1).green());
        assertEquals(i1.getPixel(1, 1).blue(), i3.getPixel(1, 1).blue());
      } catch (IOException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testGetPixel() {
    try {
      Image i0 = new ImageImpl("koala.ppm");
      assertEquals(101, i0.getPixel(0, 0).red());
      assertEquals(90, i0.getPixel(0, 0).green());
      assertEquals(58, i0.getPixel(0, 0).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i1 = new ImageImpl("sample.ppm");
      assertEquals(0, i1.getPixel(0, 0).red());
      assertEquals(255, i1.getPixel(0, 0).green());
      assertEquals(255, i1.getPixel(0, 0).blue());

      assertEquals(255, i1.getPixel(1, 0).red());
      assertEquals(0, i1.getPixel(1, 0).green());
      assertEquals(255, i1.getPixel(1, 0).blue());

      assertEquals(255, i1.getPixel(0, 1).red());
      assertEquals(0, i1.getPixel(0, 1).green());
      assertEquals(255, i1.getPixel(0, 1).blue());

      assertEquals(255, i1.getPixel(1, 1).red());
      assertEquals(255, i1.getPixel(1, 1).green());
      assertEquals(255, i1.getPixel(1, 1).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      Image i2 = new ImageImpl("sample.ppm");
      Image i3 = new ImageImpl(i2, (x, y, img, p) ->
              new RGBPixelImpl(img.getPixel(x, y).red()), 0);
      try {
        i2.save("sample-copy.ppm");
        i3.save("sample-red.ppm");

        Image i4 = new ImageImpl("sample-copy.ppm");
        Image i5 = new ImageImpl("sample-red.ppm");

        assertEquals(i2.getPixel(0, 0).red(), i4.getPixel(0, 0).red());
        assertEquals(i2.getPixel(0, 0).green(), i4.getPixel(0, 0).green());
        assertEquals(i2.getPixel(0, 0).blue(), i4.getPixel(0, 0).blue());

        assertEquals(i2.getPixel(1, 0).red(), i4.getPixel(1, 0).red());
        assertEquals(i2.getPixel(1, 0).green(), i4.getPixel(1, 0).green());
        assertEquals(i2.getPixel(1, 0).blue(), i4.getPixel(1, 0).blue());

        assertEquals(i2.getPixel(0, 1).red(), i4.getPixel(0, 1).red());
        assertEquals(i2.getPixel(0, 1).green(), i4.getPixel(0, 1).green());
        assertEquals(i2.getPixel(0, 1).blue(), i4.getPixel(0, 1).blue());

        assertEquals(i2.getPixel(1, 1).red(), i4.getPixel(1, 1).red());
        assertEquals(i2.getPixel(1, 1).green(), i4.getPixel(1, 1).green());
        assertEquals(i2.getPixel(1, 1).blue(), i4.getPixel(1, 1).blue());

        assertEquals(i5.getPixel(0, 0).red(), i3.getPixel(0, 0).red());
        assertEquals(i5.getPixel(0, 0).green(), i3.getPixel(0, 0).green());
        assertEquals(i5.getPixel(0, 0).blue(), i3.getPixel(0, 0).blue());

        assertEquals(i5.getPixel(1, 0).red(), i3.getPixel(1, 0).red());
        assertEquals(i5.getPixel(1, 0).green(), i3.getPixel(1, 0).green());
        assertEquals(i5.getPixel(1, 0).blue(), i3.getPixel(1, 0).blue());

        assertEquals(i5.getPixel(0, 1).red(), i3.getPixel(0, 1).red());
        assertEquals(i5.getPixel(0, 1).green(), i3.getPixel(0, 1).green());
        assertEquals(i5.getPixel(0, 1).blue(), i3.getPixel(0, 1).blue());

        assertEquals(i5.getPixel(1, 1).blue(), i3.getPixel(1, 1).blue());
        assertEquals(i5.getPixel(1, 1).green(), i3.getPixel(1, 1).green());
        assertEquals(i5.getPixel(1, 1).blue(), i3.getPixel(1, 1).blue());
      } catch (IOException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    //invalid testing
    try {
      Image i6 = new ImageImpl("koala.ppm");
      assertEquals(101, i6.getPixel(-1, 0).red());
      assertEquals(90, i6.getPixel(0, 3000).green());
      assertEquals(58, i6.getPixel(-100, 5000).blue());
      fail();
    } catch (IllegalArgumentException e) {
      //success
    }
  }

  @Test
  public void testGetWidth() {
    try {
      Image i0 = new ImageImpl("koala.ppm");
      assertEquals(1024, i0.getWidth());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i1 = new ImageImpl("sample.ppm");
      assertEquals(2, i1.getWidth());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testGetHeight() {
    try {
      Image i0 = new ImageImpl("koala.ppm");
      assertEquals(768, i0.getHeight());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i1 = new ImageImpl("sample.ppm");
      assertEquals(2, i1.getHeight());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

}