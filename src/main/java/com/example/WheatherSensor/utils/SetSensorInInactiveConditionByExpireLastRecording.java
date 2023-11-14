package com.example.WheatherSensor.utils;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.repository.IDataOfMeasuringRepository;
import com.example.WheatherSensor.repository.ISensorRepository;
import com.example.WheatherSensor.utilsInterfaces.ISetSensorInInactiveConditionByExpireLastRecording;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class for checking the inactive state of the sensor
 **/
@Component
public class SetSensorInInactiveConditionByExpireLastRecording implements ISetSensorInInactiveConditionByExpireLastRecording {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final int MINUTE_TO_MILLISECONDS = 60_000;
    private final ISensorRepository iSensorRepository;
    private final IDataOfMeasuringRepository iDataOfMeasuringRepository;

    @Autowired
    public SetSensorInInactiveConditionByExpireLastRecording(ISensorRepository iSensorRepository, IDataOfMeasuringRepository iDataOfMeasuringRepository) {
        this.iSensorRepository = iSensorRepository;
        this.iDataOfMeasuringRepository = iDataOfMeasuringRepository;
    }

    public boolean timeIsExpired(DataOfMeasurement dataOfMeasurement) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp actualDateInMill = Timestamp.valueOf(format.format(new Date()));
        if ((actualDateInMill.getTime() - dataOfMeasurement.getTimeInMilliseconds()) > MINUTE_TO_MILLISECONDS) {
            return true;
        } else {
            return false;
        }

    }

    @Transactional
    @Scheduled(initialDelay = 3000L, fixedDelay = 30000L)
    public void makeSensorIsInactivatedByTimeOfLastRecording() {
        List<Sensor> sensorList = iSensorRepository.findAllByActivated(true);
        if (!(sensorList.size() == 0)) {
            for (Sensor sensors : sensorList) {
                List<DataOfMeasurement> dataOfMeasurementOfSensorList = iDataOfMeasuringRepository.findBySensorId(sensors.getId());
                if (!(dataOfMeasurementOfSensorList.size() == 0)) {
                    DataOfMeasurement dataOfMeasurement = dataOfMeasurementOfSensorList.get(dataOfMeasurementOfSensorList.size() - 1);
                    if (timeIsExpired(dataOfMeasurement)) {
                        log.info("Sensor with name: " + sensors.getName() + " key: " + sensors.getKey() + " was inactivated!");
                        iSensorRepository.makeSensorIsInactivated(sensors.getId());
                    }
                }
            }
        }
    }
}