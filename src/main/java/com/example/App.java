package com.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProvider;

/**
 * Christian Barbati - Dec 2023
 * 
 * Class to add methods that make it easier to interact with
 * the Raspberry Pi Sense Hat
 */

public class App 
{
	/**
	 * Static variables representing the hex addresses of 
	 */
	static final int pressureAddress = 0x5c;
	static final int humidityAddress = 0x5f;

    public static void main( String[] args )
    {

		System.out.println("Current Temperature (C): " + getTempFromPressure());

		//System.out.println(getTempFromHumidity());

		System.out.println("Current Pressure (mbar): " + getPressureMbar());
		
    }

	/**
	 * Returns a double representing the current temperature reading in degrees Celsius, as read by the LPS25H pressure sensor
	 * @return 
	 */
	public static double getTempFromPressure(){
		double temperature = 0;

		I2C tempI2C = getI2C("TEMPFROMPRESSURE", pressureAddress);
		
		try  {

			if(!initializeLPS25H(tempI2C)){
				//Add proper exception-handling here later
				System.out.println("Error initializing LPS25H");
			}

			//Get the temperature readings from the registers and store them as Strings
			String tempStringHigh = Integer.toBinaryString(tempI2C.readRegister(0x2C));
			String tempStringLow = Integer.toBinaryString(tempI2C.readRegister(0x2B));
			
			//The readings from the registers represent 8 bit values. Add zeroes to the left hand side until the values are 8 bits
			tempStringHigh = fillEightBit(tempStringHigh);
			tempStringLow = fillEightBit(tempStringLow);

			//Concatenate the two strings to get the 16 bit value for the temperature
			String tempString = tempStringHigh + tempStringLow;

			double cycles;

			/**
			 * If the leading bit is a zero, convert from twos-complement. 
			 * 
			 * Otherwise simply convert to an integer
			 * 
			 * (Cycles is the nomenclature in LPS25H docs, so I am keeping it consistent here)
			 */
			if(tempString.charAt(0) == '1'){
				cycles = fromTwosComplement(tempString);
			}else{
				cycles = Integer.parseInt(tempString, 2); 
			}

			//Temperature offset is cycles/480, relative to a base number of 42.5 degrees Celsius
			temperature = 42.5 + (cycles/480);
		} catch (Exception e){
			System.out.println(e);
		}
		

		return temperature;
	}

	/**
	 * Initializes the appropriate registers on the LPS25H 
	 * to enable reading temperature.
	 * @param tempI2C
	 * @return
	 */
	public static boolean initializeLPS25H(I2C tempI2C){
			try{
				//Set CTRL_REG1. Enable output, set data rate to 25Hz, don't update output registers until MSB and LSB update
				tempI2C.writeRegister(0x20, 0xc4); 
				//Set RES_CONF. Set temp internal avereage to 16, pressure internal average to 32
				tempI2C.writeRegister(0x10, 0x05);
				//Set FIFO_CTRL. Set FIFO to generate a running average filtered pressure
				tempI2C.writeRegister(0x2E, 0xc0);
				//Set CTRL_REG4. Unclear what this does in RTIMU, doing it here until I can test.
				tempI2C.writeRegister(0x23, 0x40);

				return true;
			} catch (Exception e){
				System.out.println(e);
				return false;
			}


	}

	/**
	 * Returns a double representing the current pressure in mbar, as read by the LPS25H pressure sensor
	 * @return
	 */
	public static double getPressureMbar(){
		double temperature = 0;

		I2C tempI2C = getI2C("TEMPFROMPRESSURE", pressureAddress);
		
		if(!initializeLPS25H(tempI2C)){
			//Add proper exception-handling here later
			System.out.println("Error initializing LPS25H");
		}

		try  {

			int pressureH = tempI2C.readRegister(0x2A);
			

			int pressureL = tempI2C.readRegister(0x29);
			

			int pressureXL = tempI2C.readRegister(0x28);
			

			String pressureHigh = Integer.toBinaryString(pressureH);

			String pressureLow = Integer.toBinaryString(pressureL);

			String pressureExtraLow = Integer.toBinaryString(pressureXL);

			
			while(pressureHigh.length() < 8){
				pressureHigh = '0' + pressureHigh;
			}

			while(pressureLow.length() < 8){
				pressureLow = '0' + pressureLow;
			}

			while(pressureExtraLow.length() < 8){
				pressureExtraLow = '0' + pressureExtraLow;
			}

			String tempString = pressureHigh + pressureLow + pressureExtraLow;

			//System.out.println(tempString);

			double cycles;
			
			if(tempString.charAt(0) == '1'){
				cycles = fromTwosComplement(tempString);
			}else{
				cycles = Integer.parseInt(tempString, 2); 
			}

			//System.out.println(cycles);

			temperature = (cycles/4096);

			//System.out.println(temp);
		} catch (Exception e){
			System.out.println(e);
		}
		

		return temperature;
	}

	/**
	 * Returns a double representing the current temperature reading in degrees Celsius, as read by the HTS221 humidity sensor
	 * 
	 */
	public static double getTempFromHumidity(){
		double temperature = 0;

		I2C tempI2C = getI2C("TEMPFROMPRESSURE", humidityAddress);
		
		try  {

			int temperature1 = tempI2C.readRegister(0x2A);
			
			System.out.println("T LOW: " + temperature1);

			int temperature2 = tempI2C.readRegister(0x2B);
			
			System.out.println("T HIGH: " + temperature2);

			String tempStringHigh = Integer.toBinaryString(temperature2);

			String tempStringLow = Integer.toBinaryString(temperature1);
			
			while(tempStringHigh.length() < 8){
				tempStringHigh = '0' + tempStringHigh;
			}

			while(tempStringLow.length() < 8){
				tempStringLow = '0' + tempStringLow;
			}

			String tempString = tempStringHigh + tempStringLow;

			System.out.println(tempString);

			int cycles;
			
			if(tempString.charAt(0) == '1'){
				cycles = fromTwosComplement(tempString);
			}else{
				cycles = Integer.parseInt(tempString, 2); 
			}

			System.out.println(cycles);

			temperature = 42.5 + (cycles/480);

			//System.out.println(temp);
		} catch (Exception e){
			System.out.println(e);
		}
		

		return temperature;
	}

	public static I2C getI2C(String id, int address){
		I2C i2c;

		Context pi4j = Pi4J.newAutoContext();
		I2CProvider i2CProvider = pi4j.provider("linuxfs-i2c");
		I2CConfig i2cConfig = I2C.newConfigBuilder(pi4j).id(id).bus(1).device(address).build();
		i2c = i2CProvider.create(i2cConfig);

		return i2c;
	}

	/**
	 * Converts a passed binary string in two's complement to
	 * it's decimal equivalent
	 */
	public static int fromTwosComplement(String binary){
		int converted = 0;

		String twos = "", ones = "";

        for (int i = 0; i < binary.length(); i++) {
            ones += binary.charAt(i) == '0' ? "1" : "0";
        }
		
		
        StringBuilder builder = new StringBuilder(ones);
        boolean b = false;
        for (int i = ones.length() - 1; i > 0; i--) {
            if (ones.charAt(i) == '1') {
                builder.setCharAt(i, '0');
            } else {
                builder.setCharAt(i, '1');
                b = true;
                break;
            }
        }
        if (!b)
            builder.append("1", 0, 7);

        twos = builder.toString();

		converted = Integer.parseInt(twos, 2);

		//System.out.println(twos);

		converted = converted * -1;

		return converted;
	}

	static public String fillEightBit(String eightBit){
		while(eightBit.length() < 8){
			eightBit = '0' + eightBit;
		}
		return eightBit;
	}
}
