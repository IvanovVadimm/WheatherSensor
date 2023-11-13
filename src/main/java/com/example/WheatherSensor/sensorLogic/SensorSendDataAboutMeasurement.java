package com.example.WheatherSensor.sensorLogic;

import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.utilsInterfaces.IMakeRandomIsRainingOfMeasurement;
import com.example.WheatherSensor.utilsInterfaces.IMakeRandomValueOfMeasurement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that simulates the operation of a sensor when sending measuring data on a server and sending a request to the server for a specific URL
 **/
@Component
public class SensorSendDataAboutMeasurement {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SensorIsRegistering sensorIsRegistering;
    private final IMakeRandomIsRainingOfMeasurement iMakeRandomIsRainingOfMeasurement;
    private final IMakeRandomValueOfMeasurement iMakeRandomValueOfMeasurement;
    private final int randomTimeOfSending = 15_000;

    public SensorSendDataAboutMeasurement(SensorIsRegistering sensorIsRegistering, IMakeRandomIsRainingOfMeasurement iMakeRandomIsRainingOfMeasurement, IMakeRandomValueOfMeasurement iMakeRandomValueOfMeasurement) {
        this.sensorIsRegistering = sensorIsRegistering;
        this.iMakeRandomIsRainingOfMeasurement = iMakeRandomIsRainingOfMeasurement;
        this.iMakeRandomValueOfMeasurement = iMakeRandomValueOfMeasurement;

    }

    @Scheduled(initialDelay = 1000L, fixedDelay = randomTimeOfSending)
    public void voice() throws IOException {
        String urlAddress = "http://localhost:8080/sensors/" + sensorIsRegistering.getUuid() + "/measurements";
        MeasurementsRegistration measurementsRegistration = new MeasurementsRegistration();
        Double value = iMakeRandomValueOfMeasurement.randomValue();
        measurementsRegistration.setValue(value);
        Boolean isRaining = iMakeRandomIsRainingOfMeasurement.getRandomIsRaining();
        measurementsRegistration.setIsRaining(isRaining);
        String body = "{\n" +
                "    \"value\":\"" + measurementsRegistration.getValue() + "\",\n" +
                "    \"isRaining\":" + measurementsRegistration.getIsRaining()+"}";
        URL url = new URL(urlAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
            dos.writeBytes(body);
            }
        log.info("Sensor with UUID:" + sensorIsRegistering.getUuid() + " has registered value of temperature: " + value + " and was raining: " + isRaining);
    }
}