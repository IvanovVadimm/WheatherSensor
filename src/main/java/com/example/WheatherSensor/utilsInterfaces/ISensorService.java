package com.example.WheatherSensor.utilsInterfaces;

import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementIsRainingValidDataExceptions;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementValueValidDataExceptions;
import com.example.WheatherSensor.exceptions.sensorsException.SensorHasNotNameExc;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.registration.SensorRegistration;

public interface ISensorService {
    String registrationOfSensor(SensorRegistration sensorRegistration) throws SensorHasNotNameExc;

    boolean makeMeasurements(String key, MeasurementsRegistration measurementsRegistration) throws MeasurementValueValidDataExceptions, MeasurementIsRainingValidDataExceptions, SensorIsNotExistWithThatKeyExc;

    Sensor getSensorByKey(String key) throws SensorIsNotExistWithThatKeyExc;
}