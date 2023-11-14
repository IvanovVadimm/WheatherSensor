package com.example.WheatherSensor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Class describe entity sensor data from database
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sensor_table")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_table_id_seq")
    @SequenceGenerator(name = "sensor_table_id_seq", allocationSize = 1)
    private long id;
    @Column(name = "sensor_name")
    private String name;
    @Column(name = "sensor_key")
    private String key;
    @JsonIgnore
    @Column(name = "sensor_is_activated")
    private boolean activated;
    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    private Set<DataOfMeasurement> dataOfMeasurementSet;
}