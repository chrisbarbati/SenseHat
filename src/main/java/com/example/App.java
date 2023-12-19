package com.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.i2c.I2C;
import com.pi4j.io.i2c.I2CConfig;
import com.pi4j.io.i2c.I2CProvider;

public class App 
{

	static final int pressureAddress = 0x5c;
	static final int humidityAddress = 0x5f;

    public static void main( String[] args )
    {

		System.out.println(getTempFromPressure());

		//System.out.println(getTempFromHumidity());

		System.out.println(getPressureMbar());
		
    }

	/**
	 * Returns a double representing the current temperature reading in degrees Celsius, as read by the LPS25H pressure sensor
	 * 
	 */
	public static double getTempFromPressure(){
		double temperature = 0;

		I2C tempI2C = getI2C("TEMPFROMPRESSURE", pressureAddress);
		
		try  {

			int temperatureLow = tempI2C.readRegister(0x2B);
			
			System.out.println("T LOW: " + temperatureLow);

			int temperatureHigh = tempI2C.readRegister(0x2C);
			
			System.out.println("T HIGH: " + temperatureHigh);

			String tempStringHigh = Integer.toBinaryString(temperatureHigh);

			String tempStringLow = Integer.toBinaryString(temperatureLow);
			
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

	/**
	 * Returns a double representing the current pressure in mbar, as read by the LPS25H pressure sensor
	 * 
	 */
	public static double getPressureMbar(){
		double temperature = 0;

		I2C tempI2C = getI2C("TEMPFROMPRESSURE", pressureAddress);
		
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

			System.out.println(tempString);

			int cycles;
			
			if(tempString.charAt(0) == '1'){
				cycles = fromTwosComplement(tempString);
			}else{
				cycles = Integer.parseInt(tempString, 2); 
			}

			System.out.println(cycles);

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

		System.out.println(twos);

		converted = converted * -1;

		return converted;
	}
}
