# OOD Assignment 5 - Image Editor

## Interfaces

- `RGBPixel` - Defines the methods for interacting with a singular pixel of an image
- `Image` - Defines the methods for interacting with an image
- `Filter` - Defines the filter function object that we use to implement all the image operations
- `ImageEditorModel` - Defines the ways that the controller can issue commands to the model
- `ImageEditorView` - Defines how the controller can issue messages to the view to be displayed

## Classes

- `RGBPixelImpl` - Implementation of `RGBPixel`, stores colors as three integers ranging 0 - 255
- `ImageImpl` - Implementation of `Image`, stores an image as a 2d array of `RGBPixel`
- `ImageEditorModelImpl` - Implementation of `ImageEditorModel`, stores a map of `Image` and a map of `Filter`
- `ImageEditorTextView` - Supports rendering text messages to an `Appendable` output
- `ImageEditorTextController` - Supports taking in textual commands from a `Readable`
- `ImageUtil` - Supports saving and loading `.ppm` files
- `ColorTransformFilter` - DESCRIPTION
- `KernelFilter` - DESCRIPTION

