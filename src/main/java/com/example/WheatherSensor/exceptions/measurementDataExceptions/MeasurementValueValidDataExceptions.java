package com.example.WheatherSensor.exceptions.measurementDataExceptions;

/**
 * The class describes an exception when the temperature value is entered incorrectly
 **/
public class MeasurementValueValidDataExceptions extends Exception {
    @Override
    public String toString() {
        return "Measurement value data is not valid!";
    }
}