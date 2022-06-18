package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * An Implementation of ImageEditorModel, represents filters as function objects.
 */
public class ImageEditorModelImpl implements ImageEditorModel {
  private Map<String, Image> images;
  private Map<String, Filter> filters;

  /**
   * Default constructor, init hashmaps and populate filters.
   */
  public ImageEditorModelImpl() {
    images = new HashMap<String, Image>();
    filters = new HashMap<String, Filter>();

    populateFilters();
  }

  private void populateFilters() {
    filters.put("red-value", (x, y, img, p) -> new RGBPixelImpl(img.getPixel(x, y).red()));
    filters.put("green-value", (x, y, img, p) -> new RGBPixelImpl(img.getPixel(x, y).green()));
    filters.put("blue-value", (x, y, img, p) -> new RGBPixelImpl(img.getPixel(x, y).blue()));

    filters.put("value", (x, y, img, p) -> {
      RGBPixel c = img.getPixel(x, y);
      return new RGBPixelImpl(Math.max(c.red(), Math.max(c.green(), c.blue())));
    });

    filters.put("intensity", (x, y, img, p) -> {
      RGBPixel c = img.getPixel(x, y);
      return new RGBPixelImpl((c.red() + c.green() + c.blue()) / 3);
    });

    filters.put("luma", (x, y, img, p) -> {
      RGBPixel c = img.getPixel(x, y);
      return new RGBPixelImpl((int) (0.2126 * c.red() + 0.7152 * c.green() + 0.0722 * c.blue()));
    });

    filters.put("flip-vertical", (x, y, img, p) ->
            new RGBPixelImpl(img.getPixel(x, (img.getHeight() - 1) - y)));
    filters.put("flip-horizontal", (x, y, img, p) ->
            new RGBPixelImpl(img.getPixel((img.getWidth() - 1) - x, y)));

    filters.put("brighten", (x, y, img, p) -> new RGBPixelImpl(img.getPixel(x, y).red() + p,
            img.getPixel(x, y).green() + p,
            img.getPixel(x, y).blue() + p));

    filters.put("gaussian", new KernelFilter(new double[][]{
            {1 / 16.0, 1 / 8.0, 1 / 16.0},
            {1 / 8.0, 1 / 4.0, 1 / 8.0},
            {1 / 16.0, 1 / 8.0, 1 / 16.0}
    }));

    filters.put("sharpen", new KernelFilter(new double[][]{
            {1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0},
            {1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0, 1 / 8.0},
            {1 / 8.0, 1 / 4.0, 1 / 1.0, 1 / 4.0, 1 / 8.0},
            {1 / 8.0, 1 / 4.0, 1 / 4.0, 1 / 4.0 / 1 / 8.0},
            {1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0, 1 / 8.0}

    }));

    filters.put("sepia", new ColorTransformFilter(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    }));
  }

  /**
   * Load an image from a file and call it imageName.
   *
   * @param filename  name of the file to load
   * @param imageName name to call the image once it is loaded
   */
  @Override
  public void loadImage(String filename, String imageName) {
    images.put(imageName, new ImageImpl(filename));
  }

  /**
   * Save an image to a file.
   *
   * @param filename  name of file to write to
   * @param imageName name of image to save
   */
  @Override
  public void saveImage(String filename, String imageName) {
    Image toSave = images.getOrDefault(imageName, null);
    if (toSave == null) {
      throw new IllegalArgumentException("Image not found.");
    }

    try {
      toSave.save(filename);
    } catch (IOException e) {
      throw new IllegalArgumentException("Filesystem error.");
    }
  }

  /**
   * Create a new image by applying a filter to an old one.
   *
   * @param filterName  name of the filter to apply
   * @param inputImage  old image
   * @param outputImage name of image to be created
   */
  @Override
  public void filterImage(String filterName, String inputImage,
                          String outputImage, int filterParameter) {
    Filter filter = filters.getOrDefault(filterName, null);
    if (filter == null) {
      throw new IllegalArgumentException("src.Filter not found");
    }

    Image image = images.getOrDefault(inputImage, null);
    if (image == null) {
      throw new IllegalArgumentException("src.Image not found");
    }

    images.put(outputImage, new ImageImpl(image, filter, filterParameter));
  }

  /**
   * List of filter names that this model supports.
   *
   * @return list of names of filters
   */
  @Override
  public String[] listFilters() {
    return filters.keySet().toArray(new String[0]);
  }
}
