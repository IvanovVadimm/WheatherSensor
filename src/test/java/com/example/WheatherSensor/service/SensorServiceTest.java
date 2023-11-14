package com.example.WheatherSensor.service;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.registration.SensorRegistration;
import com.example.WheatherSensor.repository.IDataOfMeasuringRepository;
import com.example.WheatherSensor.repository.ISensorRepository;
import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsIsRainingOnValidData;
import com.example.WheatherSensor.utilsInterfaces.ICheckMeasurementsValueOnValidData;
import com.example.WheatherSensor.utilsInterfaces.IFilterForNameOfSensor;
import com.example.WheatherSensor.utilsInterfaces.IMakeUUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {
    @InjectMocks
    private SensorService sensorService;
    @Mock
    private  IFilterForNameOfSensor iFilterForNameOfSensor;
    @Mock
    private  ISensorRepository iSensorRepository;
    @Mock
    private  IMakeUUID iMakeUUID;
    @Mock
    private  IDataOfMeasuringRepository iDataOfMeasuringRepository;
    @Mock
    private  ICheckMeasurementsValueOnValidData iCheckMeasurementsValueOnValidData;
    @Mock
    private  ICheckMeasurementsIsRainingOnValidData iCheckMeasurementsIsRainingOnValidData;
    private SensorRegistration sensorRegistration = new SensorRegistration();
    private Sensor sensor;
    private UUID uuid;
    private String key;
    private DataOfMeasurement dataOfMeasurement = new DataOfMeasurement();
    private Set<DataOfMeasurement> dataOfMeasurementSet = new HashSet<>();
    @BeforeEach
    void setUp() {
        sensorRegistration.setName("testName");
        uuid = UUID.randomUUID();
        sensor = new Sensor();
        sensor.setId(1);
        sensor.setActivated(true);
        sensor.setName(sensorRegistration.getName());
        sensor.setKey(String.valueOf(uuid));
        sensor.setDataOfMeasurementSet(dataOfMeasurementSet);
        key = String.valueOf(uuid);
        dataOfMeasurement.setRaining(true);
    }

    @Test
    void registrationOfSensor() {
        assertFalse(key.isEmpty());
    }

    @Test
    void makeMeasurements() {
        }

    @Test
    void getSensorByKey() {
    }
}