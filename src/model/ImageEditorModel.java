package model;

/**
 * Model for an image editor that supports loading, saving, and filtering images.
 */
public interface ImageEditorModel {

  /** Load an image from a file and call it imageName.
   * @param filename name of the file to load
   * @param imageName name to call the image once it is loaded
   */
  void loadImage(String filename, String imageName);

  /** Save an image to a file.
   * @param filename name of file to write to
   * @param imageName name of image to save
   */
  void saveImage(String filename, String imageName);

  /** Create a new image by applying a filter to an old one.
   * @param filterName name of the filter to apply
   * @param inputImage old image
   * @param outputImage name of image to be created
   */
  void filterImage(String filterName, String inputImage, String outputImage, int filterParameter);

  /** List of filter names that this model supports.
   * @return list of names of filters
   */
  String[] listFilters();
}
