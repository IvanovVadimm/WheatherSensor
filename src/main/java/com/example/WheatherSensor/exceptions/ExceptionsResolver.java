package com.example.WheatherSensor.exceptions;

import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementIsRainingValidDataExceptions;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementValueValidDataExceptions;
import com.example.WheatherSensor.exceptions.sensorsException.SensorHasNotNameExc;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The class handles all exceptions thrown and logs the exceptions message
 **/
@ControllerAdvice
public class ExceptionsResolver {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(SensorHasNotNameExc.class)
    public ResponseEntity<String> SensorHasNotNameExcHand(SensorHasNotNameExc e) {
        log.error("Sensor try registering but its name was uncorrected!");
        String message = "Sensor try registering but its name was uncorrected!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SensorIsNotExistWithThatKeyExc.class)
    public ResponseEntity<String> SensorIsNotExistWithThatKeyExcHand(SensorIsNotExistWithThatKeyExc e) {
        log.error("There is no sensor with this key!");
        String message = "There is no sensor with this key!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MeasurementValueValidDataExceptions.class)
    public ResponseEntity<String> MeasurementValueValidDataExceptionsHand(MeasurementValueValidDataExceptions e) {
        log.error("This measurement value data is incorrect!");
        String message = "This measurement value data is incorrect!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MeasurementIsRainingValidDataExceptions.class)
    public ResponseEntity<String> MeasurementIsRainigValidDataExceptionsHand(MeasurementIsRainingValidDataExceptions e) {
        log.error("This measurement raining data is incorrect!");
        String message = "This measurement raining data is incorrect!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}