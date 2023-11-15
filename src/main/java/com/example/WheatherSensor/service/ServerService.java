package com.example.WheatherSensor.service;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.repository.IDataOfMeasuringRepository;
import com.example.WheatherSensor.repository.ISensorRepository;
import com.example.WheatherSensor.utilsInterfaces.ISensorService;
import com.example.WheatherSensor.utilsInterfaces.IServerService;
import com.example.WheatherSensor.utilsInterfaces.ISetSensorInInactiveConditionByExpireLastRecording;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class describing the operation of the server on the service layer
 **/
@Service
public class ServerService implements IServerService {
    private final long COUNT_LATEST_RECORDING = 20;
    private final ISensorRepository iSensorRepository;
    private final IDataOfMeasuringRepository iDataOfMeasuringRepository;
    private final ISetSensorInInactiveConditionByExpireLastRecording iSetSensorInInactiveConditionByExpireLastRecording;
    private final ISensorService iSensorService;

    @Autowired
    public ServerService(ISensorRepository iSensorRepository, IDataOfMeasuringRepository iDataOfMeasuringRepository, ISetSensorInInactiveConditionByExpireLastRecording iSetSensorInInactiveConditionByExpireLastRecording, SensorService sensorService) {
        this.iSensorRepository = iSensorRepository;
        this.iDataOfMeasuringRepository = iDataOfMeasuringRepository;
        this.iSetSensorInInactiveConditionByExpireLastRecording = iSetSensorInInactiveConditionByExpireLastRecording;
        this.iSensorService = sensorService;
    }

    public List<Sensor> getAllActiveSensors() {
        return iSensorRepository.findAllByActivated(true);
    }

    public List<DataOfMeasurement> getAllTheMostLatestMeasurementBySensorKey(String key) throws SensorIsNotExistWithThatKeyExc {
        Sensor sensor = iSensorService.getSensorByKey(key);
        return iDataOfMeasuringRepository.findTheMostLatest20BySensorId(sensor.getId(), COUNT_LATEST_RECORDING);
    }

    public List<DataOfMeasurement> getActuallyInformationAboutAllMeasurements() {
        java.sql.Date actualDate = new java.sql.Date(new Date().getTime());
        List<DataOfMeasurement> actualMeasurementList = iDataOfMeasuringRepository.findDataOfMeasurementByDateOfMeasurementEquals(actualDate);
        List<DataOfMeasurement> list =  actualMeasurementList.stream().filter(actualMeasurement -> (!iSetSensorInInactiveConditionByExpireLastRecording.timeIsExpired(actualMeasurement))).collect(Collectors.toList());
        return list;
    }
}