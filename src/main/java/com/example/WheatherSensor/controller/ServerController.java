package com.example.WheatherSensor.controller;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.service.ServerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Server controller class
 **/
@RestController
@RequestMapping("/sensors")
public class ServerController {
    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @Operation(summary = "Getting all of active sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting all of active sensors is successfully"),
    })
    @GetMapping()
    public ResponseEntity<List<Sensor>> getAllActiveSensors() {
        return new ResponseEntity<>(serverService.getAllActiveSensors(), HttpStatus.OK);
    }

    @Operation(summary = "Getting all of the most latest 20 measurements of the sensor ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting all of the most latest 20 measurements of the sensor is successfully"),
    })
    @GetMapping("/{key}/measurements")
    public ResponseEntity<List<DataOfMeasurement>> getAllOfTheMostLatestMeasurementBySensorKey(@Parameter(description = "Unique key of each sensor") @PathVariable String key) throws SensorIsNotExistWithThatKeyExc {
        return new ResponseEntity<>(serverService.getAllTheMostLatestMeasurementBySensorKey(key), HttpStatus.OK);
    }

    @Operation(summary = "Getting all of actual measurements from all of sensors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting all of actual measurements from all of sensors is successfully"),
    })
    @GetMapping("/allActual/measurements")
    public ResponseEntity<List<DataOfMeasurement>> getActuallyInformationAboutAllMeasurements() {
        return new ResponseEntity<>(serverService.getActuallyInformationAboutAllMeasurements(), HttpStatus.OK);
    }
}