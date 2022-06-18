# OOD Assignment 4 - Image Editor

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

## Example Script

When the program is executed it will await console input. Here is a list of example commands:   
  
`load res\nishil.ppm nishil`  
`flip-vertical nishil nishil-vertical`  
`save res\nishil-vertical.ppm nishil-vertical`  
`flip-horizontal nishil nishil-horizontal`  
`save res\nishil-horizontal.ppm nishil-horizontal`  
`brighten 50 nishil nishil-bright`  
`brighten -50 nishil nishil-dark`  
`luma nishil nishil-luma`  
`brighten 25 nishil-luma nishil-bright-luma`  
`save res\nishil-bright-luma.ppm nishil-bright-luma`  
`quit`  
  
