package com.example.WheatherSensor.exceptions.sensorsException;

/**
 * The class describes an exception if the sensor name was not passed
 **/
public class SensorHasNotNameExc extends Exception {
    @Override
    public String toString() {
        return "Server didn't get name of sensor!";
    }
}