package view;

import java.io.IOException;

/**
 * Image editor view interface for the view for the model.
 */
public interface ImageEditorView {
  /**
   * Renders a message.
   * @param message msg
   * @throws IOException exception for failed render
   */
  void renderMessage(String message) throws IOException;
}
