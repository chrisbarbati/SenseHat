

# SenseHATI2C - Java Library for Raspberry Pi SenseHAT

SenseHATI2C is a Java library designed to simplify accessing sensor data from the SenseHAT on a Raspberry Pi using the Pi4J library. This library aims to streamline the process of retrieving sensor information, alleviating the need to manually query I2C registers and enabling developers to focus on creating Java applications that leverage SenseHAT sensor data.

## Features

- **Simplified Sensor Data Retrieval**: Access sensor information from the SenseHAT with straightforward Java functions.
- **Compatibility**: Designed to work seamlessly with the Pi4J library for Raspberry Pi.
- **Support for SenseHAT Sensors**: Provides convenient methods to gather data from the SenseHAT's LPS25H pressure sensor and HTS221 humidity sensor.
  (Support for the LSM9DS1 accelerometer / magnetometer and LED2472G is planned and will be added in the near future.)
- **Enhanced Development Experience**: Saves time and effort by handling low-level I2C interactions, allowing developers to focus on application logic.

## Getting Started

### Prerequisites

- Raspberry Pi with SenseHAT attached
- Java Development Kit (JDK)
- Pi4J library

### Installation

1. Download SenseHATI2C.java class.
2. Include the SenseHATI2C class in your Java project and ensure it is imported where necessary. 
3. Ensure the Pi4J library is set up correctly in your project. Instructions are offered [here](https://pi4j.com/1.2/install.html) directly from Pi4J.

## Usage

### Example

Here's a simple example demonstrating how to use the SenseHATI2C library to retrieve temperature data:

```java
import com.example.sensehati2c.SenseHATI2C; //Verify this path is correct for your package structure

public class SenseHATExample {

    public static void main(String[] args) {
        try {
            double temperature = SenseHATI2C.getTempFromPressure(); //Gets the temperature from the LPS25H pressure sensor, using the static method getTempFromPressure();
            System.out.println("Temperature: " + temperature + " °C");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

## Future Plans

### Code Optimization
- **Optimization**: Code is currently designed for functionality, not optimized for performance. I want to revise the code to increase efficiency - "Make it work, make it right, make it fast"
- **Refactoring and Cleanup**: Continue to improve readability of the code and clarity of the comments, making it easier for users to determine how it works and learn from it.
- **Compatibility Updates**: Maintain compatibility with future versions of Pi4J.

### Additional Sensor Support
- **Support All SenseHAT Sensors**: Add support for the LSM9DS1 accelerometer / magnetometer and LED2472G.

## Documentation

Expanded documentation is coming soon. For now, the included functions are as follows:

 - getTempFromPressure() - Returns the current temperature in degrees Celsius as a double value, as read from the LPS25H pressure sensor.
 - getTempFromHumidity() - Returns the current temperature in degrees Celsius as a double value, as read from the HTS221 humidity sensor.
 - getTempAveraged() - Calls the getTempFromPressure() and getTempFromHumidity() functions and returns an average of the results.
 - getPressureMbar() - Returns the current pressure in millibar as a double value.
 - getPressurePSI() - Returns the current pressure in PSI as a double value.
 - getHumidity() - Returns the current relative humidity percentage as a double value.

## Acknowledgments

This project includes logic and inspiration from the [RTIMULib project](https://github.com/RPi-Distro/RTIMULib/) for handling sensor data.

I also want to extend my gratitude to [pinout.xyz](https://pinout.xyz/pinout/sense_hat) for their comprehensive reference for the Sense HAT.

## Contributing

Contributions to enhance the functionality or fix issues are welcome! Please fork the repository, make changes, and submit a pull request. For major changes, please open an issue first to discuss the proposed changes.

## License

This project is licensed under the MIT License - feel free to use, modify, and distribute this code as per the terms of the license:

```
MIT License

Copyright (c) [2024] [Christian Barbati]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Contact

For any questions, feedback, or suggestions, feel free to contact [Christian Barbati](mailto:chris.barbati@gmail.com).
