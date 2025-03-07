# StructMake Command

StructMake is a custom Minecraft command that exports your builds into a structured JSON format. Originally created for integration with CC:Tweaked, it can be used for any project that benefits from a clean, easy-to-read representation of Minecraft builds.

## Features

- **Easy Export:** Save your builds as JSON files with a simple in-game command.
- **Structured Data:** The JSON format makes integrating with other tools and workflows straightforward.
- **Automatic Saving:** Files are automatically saved to your desktop (e.g., `C:/Users/USER/OneDrive/Desktop/` on Windows).

## Building the Project

To build the project, run the following commands depending on your operating system:

- **Windows:**
  ```bash
  ./gradlew.bat build
  ```
- **Other Operating Systems:**
  ```bash
  ./gradlew build
  ```

## Usage

Once the project is built and installed, you can use the command in Minecraft with the following syntax:

```plaintext
/structmake <x1> <y1> <z1> <x2> <y2> <z2>
```

Replace `<x1> <y1> <z1>` and `<x2> <y2> <z2>` with the coordinates defining the two opposite corners of your build.

## Customization & Contributions

Feel free to modify the project to better suit your needs or to extend its functionality. Contributions are welcome but I likely won't merge it for a while.

## License

This project is open source. For more details, refer to the LICENSE file.
