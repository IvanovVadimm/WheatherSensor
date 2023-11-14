package com.example.WheatherSensor.service;

import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.repository.ISensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerServiceTest {
    @InjectMocks
    private ServerService serverService;
    private Sensor sensor = new Sensor();

    @Mock
    private ISensorRepository iSensorRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllActiveSensors() {
    }

    @Test
    void getAllTheMostLatestMeasurementBySensorKey() {
    }

    @Test
    void getActuallyInformationAboutAllMeasurements() {
    }
}