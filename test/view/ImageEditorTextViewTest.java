package view;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the view.
 */
public class ImageEditorTextViewTest {
  @Test
  public void testView() {
    Appendable output = new StringBuilder();
    ImageEditorView v1 = new ImageEditorTextView(output);
    try {
      assertEquals("", output.toString());
      v1.renderMessage("test");
      assertEquals("test", output.toString());
    }
    catch (IOException e) {
      fail();
    }
  }

}