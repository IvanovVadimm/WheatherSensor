package com.example.WheatherSensor.exceptions.measurementDataExceptions;

/**
 * The class describes an exception when the value "is it raining or not" is entered incorrectly
 **/
public class MeasurementIsRainingValidDataExceptions extends Exception {
    @Override
    public String toString() {
        return "Measurement raining data is not valid!";
    }
}