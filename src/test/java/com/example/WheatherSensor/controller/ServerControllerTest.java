package com.example.WheatherSensor.controller;

import com.example.WheatherSensor.domain.DataOfMeasurement;
import com.example.WheatherSensor.domain.Sensor;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.service.SensorService;
import com.example.WheatherSensor.service.ServerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ServerControllerTest {
    @InjectMocks
    private ServerController serverController;
    private MockMvc mockMvc;
    @Mock
    private ServerService serverService;
    private final String key = "d28452c6-94c4-4f1d-904c-23c332a2824f";
    private final Sensor sensor = new Sensor();
    private final DataOfMeasurement dataOfMeasurement = new DataOfMeasurement();
    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(serverController).build();
        sensor.setId(1);
        sensor.setActivated(true);
        sensor.setName("someName");
        dataOfMeasurement.setTimeOfMeasurement(LocalTime.now());
        dataOfMeasurement.setRaining(true);
    }

    @Test
    void getAllActiveSensors() throws Exception {
        List<Sensor> listOfSensors = new ArrayList<>();
        listOfSensors.add(sensor);
        when(serverService.getAllActiveSensors()).thenReturn(listOfSensors);
        MvcResult result = mockMvc.perform(get("/sensors"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(serverService, times(1)).getAllActiveSensors();
    }

    @Test
    void getAllOfTheMostLatestMeasurementBySensorKey() throws Exception {
        List<DataOfMeasurement> listOfDataOfMeasurement = new ArrayList<>();
        listOfDataOfMeasurement.add(dataOfMeasurement);
        when(serverService.getAllTheMostLatestMeasurementBySensorKey(key)).thenReturn(listOfDataOfMeasurement);
        MvcResult result = mockMvc.perform(get("/sensors/{key}/measurements",key))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(serverService, times(1)).getAllTheMostLatestMeasurementBySensorKey(key);
    }

    @Test
    void getActuallyInformationAboutAllMeasurements() throws Exception {
        List<DataOfMeasurement> listOfDataOfMeasurement = new ArrayList<>();
        listOfDataOfMeasurement.add(dataOfMeasurement);
        when(serverService.getActuallyInformationAboutAllMeasurements()).thenReturn(listOfDataOfMeasurement);
        MvcResult result = mockMvc.perform(get("/sensors/allActual/measurements",key))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(serverService, times(1)).getActuallyInformationAboutAllMeasurements();
    }
}