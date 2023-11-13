package com.example.WheatherSensor.repository;

import com.example.WheatherSensor.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface describing the interaction of the application with sensor data from the database
 **/
@Repository
public interface ISensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByKey(String key);

    List<Sensor> findAllByActivated(boolean isActivated);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE sensor_table SET sensor_is_activated = false WHERE id = :id", countQuery = "SELECT * FROM sensor_table")
    void makeSensorIsInactivated(long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE sensor_table SET sensor_is_activated = true WHERE id = :id", countQuery = "SELECT * FROM sensor_table")
    void makeSensorIsActivated(long id);
}