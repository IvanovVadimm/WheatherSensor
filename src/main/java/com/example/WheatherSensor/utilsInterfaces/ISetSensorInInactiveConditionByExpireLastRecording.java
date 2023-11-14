package com.example.WheatherSensor.utilsInterfaces;

import com.example.WheatherSensor.domain.DataOfMeasurement;

public interface ISetSensorInInactiveConditionByExpireLastRecording {
     boolean timeIsExpired(DataOfMeasurement dataOfMeasurement);
}
