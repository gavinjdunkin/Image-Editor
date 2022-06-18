package model;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class ImageEditorModelImplTest {
  ImageEditorModel iel;

  @Test
  public void testConstructor() {
    iel = new ImageEditorModelImpl();

    assertArrayEquals(new String[]{"red-value",
            "intensity",
            "brighten",
            "sharpen",
            "sepia",
            "green-value",
            "flip-horizontal",
            "value",
            "gaussian",
            "blue-value",
            "luma",
            "flip-vertical"}, iel.listFilters());
  }

  @Test
  public void testLoadImage() {
    iel = new ImageEditorModelImpl();

    try {
      iel.loadImage("Koala.ppm", "koala");
      try {
        iel.filterImage("red-value", "koala", "red-koala", 0);
        //success
      } catch (IllegalArgumentException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.loadImage("res\\samplepng.png", "samplepng");
      try {
        iel.filterImage("red-value", "samplepng", "red-samplepng", 0);
        //success
      } catch (IllegalArgumentException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    //invalid testing
    try {
      iel.loadImage("invalid.ppm", "invalid");
      fail();
    } catch (IllegalArgumentException e) {
      //success
    }
  }

  @Test
  public void testSaveImage() {
    iel = new ImageEditorModelImpl();

    try {
      iel.loadImage("res\\sample.ppm", "test");
      try {
        iel.filterImage("red-value", "test", "red-test", 0);
      } catch (IllegalArgumentException e) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.loadImage("res\\samplepng.png", "testpng");
      try {
        iel.filterImage("red-value", "testpng", "red-testpng", 0);
      } catch (IllegalArgumentException e) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.saveImage("sample-red.ppm", "red-test");
      iel.loadImage("sample-red.ppm", "red-test-load");

      iel.saveImage("samplepng-red.png", "red-testpng");
      iel.loadImage("samplepng-red.png", "red-testpng-load");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testFilterImage() {
    iel = new ImageEditorModelImpl();

    try {
      iel.loadImage("res\\sample.ppm", "test");
      try {
        iel.filterImage("red-value", "test", "red-test", 0);
      } catch (IllegalArgumentException e) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.loadImage("Koala.ppm", "koala");
      try {
        iel.filterImage("red-value", "koala", "red-koala", 0);
        //success
      } catch (IllegalArgumentException er) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.loadImage("res\\samplepng.png", "testpng");
      try {
        iel.filterImage("gaussian", "testpng", "gau-testpng", 0);
        iel.filterImage("sharpen", "testpng", "sha-testpng", 0);
        iel.filterImage("sepia", "testpng", "sep-testpng", 0);
      } catch (IllegalArgumentException e) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.saveImage("sample-red.ppm", "red-test");
      iel.loadImage("sample-red.ppm", "red-test-load");

      iel.saveImage("gau-samplepng.png", "gau-testpng");
      iel.loadImage("gau-samplepng.png", "gau-testpng-load");

      iel.saveImage("sha-samplepng.png", "sha-testpng");
      iel.loadImage("sha-samplepng.png", "sha-testpng-load");

      iel.saveImage("sep-samplepng.png", "sep-testpng");
      iel.loadImage("sep-samplepng.png", "sep-testpng-load");
    } catch (IllegalArgumentException e) {
      fail();
    }

    //invalid testing
    try {
      iel.saveImage("invalid", "invalid");
      fail();
    } catch (IllegalArgumentException e) {
      //success
    }
  }

  @Test
  public void testListFilters() {
    iel = new ImageEditorModelImpl();

    assertArrayEquals(new String[]{"red-value",
            "intensity",
            "brighten",
            "sharpen",
            "sepia",
            "green-value",
            "flip-horizontal",
            "value",
            "gaussian",
            "blue-value",
            "luma",
            "flip-vertical"}, iel.listFilters());

  }
}