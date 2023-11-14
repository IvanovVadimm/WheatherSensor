package com.example.WheatherSensor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 * Class describe entity measuring data from database
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data_of_weather")
public class DataOfMeasurement {
    @Id
    @GeneratedValue(generator = "data_of_weather_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "data_of_weather_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "sensor_id")
    private long sensorId;
    @Column(name = "value_of_data")
    private double valueOfData;
    @Column(name = "is_raining")
    private boolean isRaining;
    @Column(name = " day_month_year_of_measurement")
    private Date dateOfMeasurement;
    @Column(name = "time_of_measurement")
    private LocalTime timeOfMeasurement;
    @JsonIgnore
    @Column(name = "time_in_milliseconds")
    private long timeInMilliseconds;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sensor_id",insertable = false, updatable = false)
    private Sensor sensor;
}