package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.ImageEditorModel;
import controller.ImageEditorTextController;
import model.ImageEditorModelImpl;
import view.ImageEditorTextView;
import view.ImageEditorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test controller using mocks.
 */
public class ControllerTest {

  @Test
  public void testMockController() {
    StringBuilder modelLog = new StringBuilder();
    ImageEditorModel model = new ModelMock(modelLog);

    StringBuilder viewLog = new StringBuilder();
    ImageEditorView view = new ViewMock(viewLog);

    StringReader input = new StringReader("load image.ppm image1\n" +
                                          "save image3.ppm image1\n" +
                                          "brighten 10 image1 image2\n" +
                                          "flip-vertical image2 image3\n" +
                                          "\n" +
                                          "greyscale image3\n" +
                                          "save image10.ppm image3 image 20 image20\n" +
                                          "quit\n");

    ImageEditorTextController c = new ImageEditorTextController(model, view, input);
    c.start();

    String expectedModelLog = "load image.ppm image1\n" +
                              "save image3.ppm image1\n" +
                              "filter brighten image1 image2 10\n" +
                              "filter flip-vertical image2 image3 0\n" +
                              "save image10.ppm image3\n";
    assertEquals(expectedModelLog, modelLog.toString());

    String expectedViewLog =  "Enter command: " +
                              "Enter command: " +
                              "Enter command: " +
                              "Enter command: " +
                              "Enter command: " +
                              "Too few arguments entered. (type 'help' for help)\n" +
                              "Enter command: " +
                              "Too few arguments entered. (type 'help' for help)\n" +
                              "Enter command: " +
                              "Enter command: ";

    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void testController() {
    ImageEditorModel model = new ImageEditorModelImpl();

    Appendable output = new StringBuilder();
    ImageEditorView view = new ImageEditorTextView(output);

    StringReader input = new StringReader("load sample.ppm image1\n" +
            "save image3.ppm image1\n" +
            "red-value image1 image2\n" +
            "flip-vertical image2 image3\n" +
            "\n" +
            "greyscale image3\n" +
            "save image10.ppm image3\n" +
            "quit\n");

    ImageEditorTextController c = new ImageEditorTextController(model, view, input);
    try {
      c.start();
    }
    catch (IllegalArgumentException e) {
      fail();
    }
  }

  private class ViewMock implements ImageEditorView {
    StringBuilder log;

    public ViewMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void renderMessage(String message) throws IOException {
      log.append(message);
    }
  }

  private class ModelMock implements ImageEditorModel {
    StringBuilder log;

    public ModelMock(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void loadImage(String filename, String imageName) {
      log.append(String.format("load %s %s\n", filename, imageName));
    }

    @Override
    public void saveImage(String filename, String imageName) {
      log.append(String.format("save %s %s\n", filename, imageName));
    }

    @Override
    public void filterImage(String filterName,
                            String inputImage, String outputImage, int filterParameter) {
      log.append(String.format("filter %s %s %s %d\n",
              filterName, inputImage, outputImage, filterParameter));
    }

    @Override
    public String[] listFilters() {
      log.append("listFilters\n");
      return new String[0];
    }
  }
}
