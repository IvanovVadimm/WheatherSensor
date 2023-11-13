package com.example.WheatherSensor.repository;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

/**
 * Interface describing the interaction of the application with weather data from the database
 **/
public interface IDataOfMeasuringRepository extends JpaRepository<DataOfMeasurement, Long> {
    boolean existsByTimeOfMeasurement(LocalTime localTime);

    @Query(nativeQuery = true, value = "SELECT * FROM data_of_weather WHERE sensor_id = :sensorId ORDER BY id DESC LIMIT :countOfLastRecording")
    List<DataOfMeasurement> findTheMostLatest20BySensorId(long sensorId, long countOfLastRecording);

    List<DataOfMeasurement> findBySensorId(long id);

    List<DataOfMeasurement> findDataOfMeasurementByDateOfMeasurementEquals(Date actualDate);
}