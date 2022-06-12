package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.ImageEditorModel;
import model.ImageEditorModelImpl;
import view.ImageEditorTextView;
import view.ImageEditorView;

/**
 * Text based controller designed for use as a console program.
 */
public class ImageEditorTextController {

  ImageEditorModel model;
  ImageEditorView view;
  Scanner input;

  /**
   * Constructor.
   *
   * @param model model to control
   * @param view  view to output to
   * @param in    input source (normally System.in)
   */
  public ImageEditorTextController(ImageEditorModel model, ImageEditorView view, Readable in) {
    this.model = model;
    this.view = view;

    input = new Scanner(in);
  }

  /**
   * Start requesting input from the input source.
   */
  public void start() {
    boolean quit = false;

    while (!quit) {
      message("Enter command: ");
      String[] command = input.nextLine().split(" ");

      try {
        try {
          switch (command[0].toLowerCase()) {
            case "save":
              model.saveImage(command[1], command[2]);
              break;
            case "load":
              model.loadImage(command[1], command[2]);
              break;
            case "help":
              printHelp();
              break;
            case "quit":
              quit = true;
              break;
            default:
              int i = 1;
              Integer parameter = parse(command[i]);
              if (parameter == null) {
                parameter = 0;
              } else {
                i++;
              }

              model.filterImage(command[0].toLowerCase(), command[i], command[i + 1], parameter);

          }
        } catch (ArrayIndexOutOfBoundsException e) {
          message("Too few arguments entered. (type 'help' for help)\n");
        }
      } catch (IllegalArgumentException e) {
        message("error: " + e.getMessage() + "\n");
      }
    }
  }

  private void printHelp() {
    String[] filters = model.listFilters();
    message("\nAvailable Commands:\n");
    message("\t[filter-name] (optional parameter) [input-image] [output-image]\n");
    message("\tload [filename] [image-name]\n");
    message("\tsave [filename] [image-name]\n");
    message("\thelp\n");
    message("\tquit\n");
    message("\nAvailable filters:\n");
    for (int i = 0; i < filters.length; i++) {
      message("\t" + filters[i] + "\n");
    }
    message("\n");
  }

  private Integer parse(String arg) {
    try {
      return Integer.parseInt(arg);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  private void message(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Output not available");
    }
  }

  /**
   * Entry point to program.
   *
   * @param args commandline arguments
   */
  public static void main(String[] args) {
    ImageEditorModel model = new ImageEditorModelImpl();
    ImageEditorView view = new ImageEditorTextView(System.out);
    ImageEditorTextController controller =
            new ImageEditorTextController(model, view, new InputStreamReader(System.in));
    controller.start();
  }
}
