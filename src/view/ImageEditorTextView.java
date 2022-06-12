package view;

import java.io.IOException;

import view.ImageEditorView;

/**
 * Implements imageeditorview interface.
 */
public class ImageEditorTextView implements ImageEditorView {

  Appendable output;

  /**
   * default constructor.
   * @param output appendable output
   */
  public ImageEditorTextView(Appendable output) {
    this.output = output;
  }

  /**
   * Renders a message.
   * @param message msg
   */
  @Override
  public void renderMessage(String message) throws IOException {
    output.append(message);
  }
}
