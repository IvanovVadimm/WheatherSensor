package com.example.WheatherSensor.controller;

import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementIsRainingValidDataExceptions;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementValueValidDataExceptions;
import com.example.WheatherSensor.exceptions.sensorsException.SensorHasNotNameExc;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.registration.SensorRegistration;
import com.example.WheatherSensor.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sensor controller class
 **/
@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Operation(summary = "Registering of sensor on a server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registering of a sensor is successfully"),
    })
    @PostMapping("/registration")
    public ResponseEntity<String> registrationOfSensor(@RequestBody SensorRegistration sensorRegistration) throws SensorHasNotNameExc {
        String UuidOfSensor = sensorService.registrationOfSensor(sensorRegistration);
        return new ResponseEntity<>(UuidOfSensor, HttpStatus.OK);
    }

    @Operation(summary = "Registering of measuring data on a server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registering of a measuring data  is successfully"),
            @ApiResponse(responseCode = "400", description = "Registering of a measuring data  is unsuccessfully"),
    })
    @PostMapping("/{key}/measurements")
    public ResponseEntity makeMeasurements(@Parameter(description = "Unique key of each sensor") @PathVariable String key, @RequestBody MeasurementsRegistration measurementsRegistration) throws SensorIsNotExistWithThatKeyExc, MeasurementIsRainingValidDataExceptions, MeasurementValueValidDataExceptions {
        return (sensorService.makeMeasurements(key, measurementsRegistration)) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}