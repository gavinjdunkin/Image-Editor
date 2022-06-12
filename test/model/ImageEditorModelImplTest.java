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
            "green-value",
            "flip-horizontal",
            "value",
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
      iel.loadImage("sample.ppm", "test");
      try {
        iel.filterImage("red-value", "test", "red-test", 0);
      } catch (IllegalArgumentException e) {
        fail();
      }
    } catch (IllegalArgumentException e) {
      fail();
    }

    try {
      iel.saveImage("sample-red.ppm", "red-test");
      iel.loadImage("sample-red.ppm", "red-test-load");
    } catch (IllegalArgumentException e) {
      fail();
    }
  }

  @Test
  public void testFilterImage() {
    iel = new ImageEditorModelImpl();

    try {
      iel.loadImage("sample.ppm", "test");
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
      iel.saveImage("sample-red.ppm", "red-test");
      iel.loadImage("sample-red.ppm", "red-test-load");
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
            "green-value",
            "flip-horizontal",
            "value",
            "blue-value",
            "luma",
            "flip-vertical"}, iel.listFilters());

  }
}