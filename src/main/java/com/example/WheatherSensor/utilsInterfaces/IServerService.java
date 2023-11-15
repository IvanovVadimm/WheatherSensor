package com.example.WheatherSensor.utilsInterfaces;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;

import java.util.List;

public interface IServerService {
    List<Sensor> getAllActiveSensors();

    List<DataOfMeasurement> getAllTheMostLatestMeasurementBySensorKey(String key) throws SensorIsNotExistWithThatKeyExc;

    List<DataOfMeasurement> getActuallyInformationAboutAllMeasurements();
}
