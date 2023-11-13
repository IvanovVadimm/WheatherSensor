package com.example.WheatherSensor.controller;

import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementIsRainingValidDataExceptions;
import com.example.WheatherSensor.exceptions.measurementDataExceptions.MeasurementValueValidDataExceptions;
import com.example.WheatherSensor.exceptions.sensorsException.SensorHasNotNameExc;
import com.example.WheatherSensor.exceptions.sensorsException.SensorIsNotExistWithThatKeyExc;
import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.registration.SensorRegistration;
import com.example.WheatherSensor.service.SensorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SensorControllerTest {
    @InjectMocks
    private SensorController sensorController;
    private MockMvc mockMvc;
    @Mock
    private SensorService sensorService;
    private final String uuid = "d28452c6-94c4-4f1d-904c-23c332a2824f";
    private final SensorRegistration sensorRegistration = new SensorRegistration();
    private final MeasurementsRegistration measurementsRegistration = new MeasurementsRegistration();

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(sensorController).build();
        sensorRegistration.setName("SomeName");
    }

    @Test
    void registrationOfSensor() throws Exception {
        when(sensorService.registrationOfSensor(any())).thenReturn(uuid);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(sensorRegistration);
        MvcResult result = mockMvc.perform(post("/sensors/registration").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(sensorService, times(1)).registrationOfSensor(any());
    }


    @Test
    void makeMeasurements() throws Exception {
        when(sensorService.makeMeasurements(anyString(), any(MeasurementsRegistration.class))).thenReturn(true);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(measurementsRegistration);
        MvcResult result = mockMvc.perform(post("/sensors/{key}/measurements", uuid).contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(sensorService, times(1)).makeMeasurements(uuid, measurementsRegistration);
    }
}