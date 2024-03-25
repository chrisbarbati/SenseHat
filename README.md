

# SenseHAT - Java Library for Raspberry Pi SenseHAT

SenseHAT is a Java library designed to simplify accessing sensor data from the SenseHAT on a Raspberry Pi using the Pi4J library.

My goal is to deliver an experience similar to the [Python SenseHAT library](https://pypi.org/project/sense-hat/), making it easier and quicker for developers to get sensor data without needing to manually query I2C registers.

For a real-world example, check out my [Spring Boot Weather API](https://github.com/chrisbarbati/WeatherServer). You can check out the [hosted version](https://chrisbarbati.ddns.net:2048/API/weather), currently running on a Raspberry Pi at my home, delivering real-time weather data.

## Features

- **Simplified Sensor Reading Retrieval**: Access sensor information from the SenseHAT with straightforward Java functions.
- **Support for SenseHAT Sensors**: Provides convenient methods to gather data from the SenseHAT's LPS25H pressure sensor and HTS221 humidity sensor.
  (Support for the LSM9DS1 accelerometer / magnetometer and LED2472G is planned and will be added in the near future.)
- **Simplified Development Experience**: Saves time and effort by handling low-level I2C interactions, allowing developers to focus on application logic.

## Getting Started

### Prerequisites

- Raspberry Pi with SenseHAT attached
- Java Development Kit (JDK)
- Pi4J library

### Installation (manual)

1. Download the appropriate JAR file from releases (recommended that you select the newest)
2. Save file within your project folder
3. Add to your classpath in your IDE

### Installation (Maven)

1. Download the appropriate JAR file from releases (recommended that you select the newest)
2. Add to your Maven repository (~.m2/repository/com/chrisbarbati/sensehat/{version number here})
3. Add Maven dependency:

```
<dependency>
            <groupId>com.chrisbarbati</groupId>
            <artifactId>sensehat</artifactId>
            <version>1.0</version>
</dependency>
```

4. Update Maven repository if necessary, and reload project

## Usage

### Example

Here's a simple example demonstrating how to use the SenseHAT library to retrieve temperature data:

```java
package com.chrisbarbati.SenseHAT; //Your package name here

import com.chrisbarbati.SenseHAT.Units.TempUnits;

public class Main {
    public static void main(String[] args){
        SenseHAT hat = new SenseHAT();
        System.out.println(hat.getTempFromPressure(TempUnits.CELSIUS));
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

 - getTempFromPressure(TempUnits unit) - Returns the current temperature in degrees Celsius as a double value, as read from the LPS25H pressure sensor.
 - getTempFromHumidity(TempUnits unit) - Returns the current temperature in degrees Celsius as a double value, as read from the HTS221 humidity sensor.
 - getPressure(PressureUnits unit)
 - getHumidity() - Returns the current relative humidity percentage as a double value.

## Testing

If you intend to use unit testing in your project (and you should), please note that unit tests on any of your methods that cause this library to call the I2C bus will always fail if you are not developing on actual hardware. For example, calling getTempFromPressure() in a test run on a Windows machine will always fail as the Windows machine does not have an I2C bus. This does not mean that your application won't work, simply that you will need to test it on an actual Raspberry Pi.

I highly recommend using remote development if possible, as compiling the application and running tests on the Raspberry Pi itself should allow you to test thoroughly without encountering this issue.

## Acknowledgments

This project includes some logic and inspiration from the [RTIMULib project](https://github.com/RPi-Distro/RTIMULib/) for handling sensor data.

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
