package com.example.WheatherSensor.service;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementIsRainingValidDataExceptions;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementValueValidDataExceptions;
import com.example.WheatherSensor.exceptions.sensorsException.SensorHasNotNameExc;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.repository.IDataOfMeasuringRepository;
import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsIsRainingOnValidData;
import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsValueOnValidData;
import com.example.WheatherSensor.utilsInterfaces.IFilterForNameOfSensor;
import com.example.WheatherSensor.utilsInterfaces.IMakeUUID;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.registration.SensorRegistration;
import com.example.WheatherSensor.repository.ISensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

/**
 * A class describing the operation of the sensor on the service layer
 **/
@Service
public class SensorService {
    private final IFilterForNameOfSensor iFilterForNameOfSensor;
    private final ISensorRepository iSensorRepository;
    private final IMakeUUID iMakeUUID;
    private final IDataOfMeasuringRepository iDataOfMeasuringRepository;
    private final ICheckMeasurementsValueOnValidData iCheckMeasurementsValueOnValidData;
    private final ICheckMeasurementsIsRainingOnValidData iCheckMeasurementsIsRainingOnValidData;

    @Autowired
    public SensorService(IFilterForNameOfSensor iFilterForNameOfSensor, ISensorRepository iSensorRepository, IMakeUUID iMakeUUID, IDataOfMeasuringRepository iDataOfMeasuringRepository, ICheckMeasurementsValueOnValidData iCheckMeasurementsValueOnValidData, ICheckMeasurementsIsRainingOnValidData iCheckMeasurementsIsRainingOnValidData) {
        this.iFilterForNameOfSensor = iFilterForNameOfSensor;
        this.iSensorRepository = iSensorRepository;
        this.iMakeUUID = iMakeUUID;
        this.iDataOfMeasuringRepository = iDataOfMeasuringRepository;
        this.iCheckMeasurementsValueOnValidData = iCheckMeasurementsValueOnValidData;
        this.iCheckMeasurementsIsRainingOnValidData = iCheckMeasurementsIsRainingOnValidData;
    }

    public String registrationOfSensor(SensorRegistration sensorRegistration) throws SensorHasNotNameExc {
        Sensor sensor = new Sensor();
        if (!iFilterForNameOfSensor.rightNameOfSensor(sensorRegistration.getName())) {
            throw new SensorHasNotNameExc();
        }
        sensor.setName(sensorRegistration.getName());
        UUID uuid = iMakeUUID.makeUUID();
        sensor.setKey(String.valueOf(uuid));
        iSensorRepository.save(sensor);
        return String.valueOf(uuid);
    }

    @Transactional
    public boolean makeMeasurements(String key, MeasurementsRegistration measurementsRegistration) throws SensorIsNotExistWithThatKeyExc, MeasurementValueValidDataExceptions, MeasurementIsRainingValidDataExceptions {
        Sensor sensor = getSensorByKey(key);
        Double valueData = measurementsRegistration.getValue();
        Boolean isRaining = measurementsRegistration.getIsRaining();
        if ((!iCheckMeasurementsValueOnValidData.rightValueOfMeasurement(valueData))) {
            throw new MeasurementValueValidDataExceptions();
        }
        if ((!iCheckMeasurementsValueOnValidData.rightValueOfMeasurement(valueData)) || (!iCheckMeasurementsIsRainingOnValidData.rightIsRainingOfMeasurement(isRaining))) {
            throw new MeasurementIsRainingValidDataExceptions();
        }
        long sensorId = sensor.getId();
        DataOfMeasurement dataOfMeasurement = new DataOfMeasurement();
        dataOfMeasurement.setSensorId(sensorId);
        dataOfMeasurement.setValueOfData(measurementsRegistration.getValue());
        dataOfMeasurement.setRaining(measurementsRegistration.getIsRaining());
        dataOfMeasurement.setDateOfMeasurement(new Date(new java.util.Date().getTime()));
        LocalTime measurementHoldTime = LocalTime.now();
        Timestamp fullTimeOfMeasurement = Timestamp.valueOf(LocalDateTime.now());
        dataOfMeasurement.setTimeOfMeasurement(measurementHoldTime);
        dataOfMeasurement.setFullTimeOfMeasurement(fullTimeOfMeasurement);
        iDataOfMeasuringRepository.save(dataOfMeasurement);
        iSensorRepository.makeSensorIsActivated(sensorId);
        return iDataOfMeasuringRepository.existsByTimeOfMeasurement(measurementHoldTime);
    }

    public Sensor getSensorByKey(String key) throws SensorIsNotExistWithThatKeyExc {
        Optional<Sensor> sensor = iSensorRepository.findByKey(key);
        if (sensor.isPresent()) {
            return sensor.get();
        } else {
            throw new SensorIsNotExistWithThatKeyExc();
        }
    }
}