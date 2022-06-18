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
      Image i1 = new ImageImpl(i0, (x, y, img, p) ->
                      new RGBPixelImpl(img.getPixel(x, (img.getHeight() - 1) - y)), 0);
      assertEquals(218, i1.getPixel(0, 0).red());
      assertEquals(199, i1.getPixel(0, 0).green());
      assertEquals(185, i1.getPixel(0, 0).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i0 = new ImageImpl("koala.ppm");
      Image i1 = new ImageImpl(i0, (x, y, img, p) ->
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
      Image i3 = new ImageImpl("res\\sample.ppm");
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

    try {
      Image i4 = new ImageImpl("res\\samplepng.png");
      //gaussian
      Image i5 = new ImageImpl(i4, new KernelFilter(new double[][]{
              {1 / 16.0, 1 / 8.0, 1 / 16.0},
              {1 / 8.0, 1 / 4.0, 1 / 8.0},
              {1 / 16.0, 1 / 8.0, 1 / 16.0}
      }), 0);
      assertEquals(0, i4.getPixel(0, 0).red());
      assertEquals(255, i4.getPixel(0, 0).green());
      assertEquals(255, i4.getPixel(0, 0).blue());

      assertEquals(255, i4.getPixel(1, 0).red());
      assertEquals(0, i4.getPixel(1, 0).green());
      assertEquals(255, i4.getPixel(1, 0).blue());

      assertEquals(255, i4.getPixel(0, 1).red());
      assertEquals(0, i4.getPixel(0, 1).green());
      assertEquals(255, i4.getPixel(0, 1).blue());

      assertEquals(255, i4.getPixel(1, 1).red());
      assertEquals(255, i4.getPixel(1, 1).green());
      assertEquals(255, i4.getPixel(1, 1).blue());

      assertEquals(79, i5.getPixel(0, 0).red());
      assertEquals(79, i5.getPixel(0, 0).green());
      assertEquals(143, i5.getPixel(0, 0).blue());

      assertEquals(111, i5.getPixel(1, 0).red());
      assertEquals(63, i5.getPixel(1, 0).green());
      assertEquals(143, i5.getPixel(1, 0).blue());

      assertEquals(111, i5.getPixel(0, 1).red());
      assertEquals(63, i5.getPixel(0, 1).green());
      assertEquals(143, i5.getPixel(0, 1).blue());

      assertEquals(127, i5.getPixel(1, 1).red());
      assertEquals(79, i5.getPixel(1, 1).green());
      assertEquals(143, i5.getPixel(1, 1).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      Image i6 = new ImageImpl("res\\samplejpeg.jpg");
      //sharpen
      Image i7 = new ImageImpl(i6, new KernelFilter(new double[][]{
              {1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0},
              {1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, 1 / 8.0},
              {1 / 8.0, 1 / 4.0, 1 / 1.0, 1 / 4.0, 1 / 8.0},
              {1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0 / 1 / 8.0},
              {1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0}

      }), 0);
      assertEquals(1, i6.getPixel(0, 0).red());
      assertEquals(255, i6.getPixel(0, 0).green());
      assertEquals(252, i6.getPixel(0, 0).blue());

      assertEquals(253, i6.getPixel(1, 0).red());
      assertEquals(0, i6.getPixel(1, 0).green());
      assertEquals(253, i6.getPixel(1, 0).blue());

      assertEquals(255, i6.getPixel(0, 1).red());
      assertEquals(1, i6.getPixel(0, 1).green());
      assertEquals(255, i6.getPixel(0, 1).blue());

      assertEquals(246, i6.getPixel(1, 1).red());
      assertEquals(247, i6.getPixel(1, 1).green());
      assertEquals(255, i6.getPixel(1, 1).blue());

      assertEquals(135, i7.getPixel(0, 0).red());
      assertEquals(255, i7.getPixel(0, 0).green());
      assertEquals(255, i7.getPixel(0, 0).blue());

      assertEquals(255, i7.getPixel(1, 0).red());
      assertEquals(125, i7.getPixel(1, 0).green());
      assertEquals(255, i7.getPixel(1, 0).blue());

      assertEquals(255, i7.getPixel(0, 1).red());
      assertEquals(126, i7.getPixel(0, 1).green());
      assertEquals(255, i7.getPixel(0, 1).blue());

      assertEquals(255, i7.getPixel(1, 1).red());
      assertEquals(255, i7.getPixel(1, 1).green());
      assertEquals(255, i7.getPixel(1, 1).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
    try {
      Image i8 = new ImageImpl("res\\samplejpeg.jpg");
      //sharpen
      Image i9 = new ImageImpl(i8, new ColorTransformFilter(new double[][]{
              {0.393, 0.769, 0.189},
              {0.349, 0.686, 0.168},
              {0.272, 0.534, 0.131}
      }), 0);
      assertEquals(1, i8.getPixel(0, 0).red());
      assertEquals(255, i8.getPixel(0, 0).green());
      assertEquals(252, i8.getPixel(0, 0).blue());

      assertEquals(253, i8.getPixel(1, 0).red());
      assertEquals(0, i8.getPixel(1, 0).green());
      assertEquals(253, i8.getPixel(1, 0).blue());

      assertEquals(255, i8.getPixel(0, 1).red());
      assertEquals(1, i8.getPixel(0, 1).green());
      assertEquals(255, i8.getPixel(0, 1).blue());

      assertEquals(246, i8.getPixel(1, 1).red());
      assertEquals(247, i8.getPixel(1, 1).green());
      assertEquals(255, i8.getPixel(1, 1).blue());

      assertEquals(244, i9.getPixel(0, 0).red());
      assertEquals(217, i9.getPixel(0, 0).green());
      assertEquals(169, i9.getPixel(0, 0).blue());

      assertEquals(147, i9.getPixel(1, 0).red());
      assertEquals(130, i9.getPixel(1, 0).green());
      assertEquals(101, i9.getPixel(1, 0).blue());

      assertEquals(149, i9.getPixel(0, 1).red());
      assertEquals(132, i9.getPixel(0, 1).green());
      assertEquals(103, i9.getPixel(0, 1).blue());

      assertEquals(255, i9.getPixel(1, 1).red());
      assertEquals(255, i9.getPixel(1, 1).green());
      assertEquals(232, i9.getPixel(1, 1).blue());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testSave() {
    try {
      Image i0 = new ImageImpl("res\\sample.ppm");
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

    try {
      Image i4 = new ImageImpl("res\\samplepng.png");
      Image i5 = new ImageImpl(i4, (x, y, img, p) ->
              new RGBPixelImpl(img.getPixel(x, y).red()), 0);
      try {
        i4.save("samplepng-copy.png");
        i5.save("samplepng-copy.ppm");

        Image i6 = new ImageImpl("samplepng-copy.png");
        Image i7 = new ImageImpl("samplepng-copy.ppm");

        assertEquals(i4.getPixel(0, 0).red(), i6.getPixel(0, 0).red());
        assertEquals(i4.getPixel(0, 0).green(), i6.getPixel(0, 0).green());
        assertEquals(i4.getPixel(0, 0).blue(), i6.getPixel(0, 0).blue());

        assertEquals(i4.getPixel(1, 0).red(), i6.getPixel(1, 0).red());
        assertEquals(i4.getPixel(1, 0).green(), i6.getPixel(1, 0).green());
        assertEquals(i4.getPixel(1, 0).blue(), i6.getPixel(1, 0).blue());

        assertEquals(i4.getPixel(0, 1).red(), i6.getPixel(0, 1).red());
        assertEquals(i4.getPixel(0, 1).green(), i6.getPixel(0, 1).green());
        assertEquals(i4.getPixel(0, 1).blue(), i6.getPixel(0, 1).blue());

        assertEquals(i4.getPixel(1, 1).red(), i6.getPixel(1, 1).red());
        assertEquals(i4.getPixel(1, 1).green(), i6.getPixel(1, 1).green());
        assertEquals(i4.getPixel(1, 1).blue(), i6.getPixel(1, 1).blue());

        assertEquals(i5.getPixel(0, 0).red(), i7.getPixel(0, 0).red());
        assertEquals(i5.getPixel(0, 0).green(), i7.getPixel(0, 0).green());
        assertEquals(i5.getPixel(0, 0).blue(), i7.getPixel(0, 0).blue());

        assertEquals(i5.getPixel(1, 0).red(), i7.getPixel(1, 0).red());
        assertEquals(i5.getPixel(1, 0).green(), i7.getPixel(1, 0).green());
        assertEquals(i5.getPixel(1, 0).blue(), i7.getPixel(1, 0).blue());

        assertEquals(i5.getPixel(0, 1).red(), i7.getPixel(0, 1).red());
        assertEquals(i5.getPixel(0, 1).green(), i7.getPixel(0, 1).green());
        assertEquals(i5.getPixel(0, 1).blue(), i7.getPixel(0, 1).blue());

        assertEquals(i5.getPixel(1, 1).blue(), i7.getPixel(1, 1).blue());
        assertEquals(i5.getPixel(1, 1).green(), i7.getPixel(1, 1).green());
        assertEquals(i5.getPixel(1, 1).blue(), i7.getPixel(1, 1).blue());
      } catch (IOException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      Image i8 = new ImageImpl("res\\sample.ppm");
      Image i9 = new ImageImpl(i8, (x, y, img, p) ->
              new RGBPixelImpl(img.getPixel(x, y).red()), 0);
      try {
        i8.save("sample-copypng.png");
        i9.save("sample-copyjpg.jpg");

        Image i10 = new ImageImpl("sample-copypng.png");
        Image i11 = new ImageImpl("sample-copyjpg.jpg");

        assertEquals(i8.getPixel(0, 0).red(), i10.getPixel(0, 0).red());
        assertEquals(i8.getPixel(0, 0).green(), i10.getPixel(0, 0).green());
        assertEquals(i8.getPixel(0, 0).blue(), i10.getPixel(0, 0).blue());

        assertEquals(i8.getPixel(1, 0).red(), i10.getPixel(1, 0).red());
        assertEquals(i8.getPixel(1, 0).green(), i10.getPixel(1, 0).green());
        assertEquals(i8.getPixel(1, 0).blue(), i10.getPixel(1, 0).blue());

        assertEquals(i8.getPixel(0, 1).red(), i10.getPixel(0, 1).red());
        assertEquals(i8.getPixel(0, 1).green(), i10.getPixel(0, 1).green());
        assertEquals(i8.getPixel(0, 1).blue(), i10.getPixel(0, 1).blue());

        assertEquals(i8.getPixel(1, 1).red(), i10.getPixel(1, 1).red());
        assertEquals(i8.getPixel(1, 1).green(), i10.getPixel(1, 1).green());
        assertEquals(i8.getPixel(1, 1).blue(), i10.getPixel(1, 1).blue());

        //fails due to jpeg compression
        assertEquals(i9.getPixel(0, 0).red(), i11.getPixel(0, 0).red());
        assertEquals(i9.getPixel(0, 0).green(), i11.getPixel(0, 0).green());
        assertEquals(i9.getPixel(0, 0).blue(), i11.getPixel(0, 0).blue());

        assertEquals(i9.getPixel(1, 0).red(), i11.getPixel(1, 0).red());
        assertEquals(i9.getPixel(1, 0).green(), i11.getPixel(1, 0).green());
        assertEquals(i9.getPixel(1, 0).blue(), i11.getPixel(1, 0).blue());

        assertEquals(i9.getPixel(0, 1).red(), i11.getPixel(0, 1).red());
        assertEquals(i9.getPixel(0, 1).green(), i11.getPixel(0, 1).green());
        assertEquals(i9.getPixel(0, 1).blue(), i11.getPixel(0, 1).blue());

        assertEquals(i9.getPixel(1, 1).blue(), i11.getPixel(1, 1).blue());
        assertEquals(i9.getPixel(1, 1).green(), i11.getPixel(1, 1).green());
        assertEquals(i9.getPixel(1, 1).blue(), i11.getPixel(1, 1).blue());
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
      Image i1 = new ImageImpl("res\\sample.ppm");
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
      Image i2 = new ImageImpl("res\\sample.ppm");
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
      Image i1 = new ImageImpl("res\\sample.ppm");
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
      Image i1 = new ImageImpl("res\\sample.ppm");
      assertEquals(2, i1.getHeight());
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

}