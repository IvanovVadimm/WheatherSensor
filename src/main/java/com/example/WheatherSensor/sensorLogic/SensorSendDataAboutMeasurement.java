
package com.example.WheatherSensor.sensorLogic;

import com.example.WheatherSensor.registration.MeasurementsRegistration;
import com.example.WheatherSensor.utilsInterfaces.IMakeRandomIsRainingOfMeasurement;
import com.example.WheatherSensor.utilsInterfaces.IMakeRandomValueOfMeasurement;
import com.example.WheatherSensor.utilsInterfaces.ISensorIsRegistering;
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
    private final ISensorIsRegistering iSensorIsRegistering;
    private final IMakeRandomIsRainingOfMeasurement iMakeRandomIsRainingOfMeasurement;
    private final IMakeRandomValueOfMeasurement iMakeRandomValueOfMeasurement;
    private final int TIME_OF_SENDING_DATA = 15_000;

    public SensorSendDataAboutMeasurement(ISensorIsRegistering iSensorIsRegistering, IMakeRandomIsRainingOfMeasurement iMakeRandomIsRainingOfMeasurement, IMakeRandomValueOfMeasurement iMakeRandomValueOfMeasurement) {
        this.iSensorIsRegistering = iSensorIsRegistering;
        this.iMakeRandomIsRainingOfMeasurement = iMakeRandomIsRainingOfMeasurement;
        this.iMakeRandomValueOfMeasurement = iMakeRandomValueOfMeasurement;

    }

    @Scheduled(initialDelay = 2000L, fixedDelay = TIME_OF_SENDING_DATA)
    public void voice() throws IOException {
        String urlAddress = "http://localhost:8080/sensors/" + iSensorIsRegistering.getUuid() + "/measurements";
        MeasurementsRegistration measurementsRegistration = new MeasurementsRegistration();
        double value = iMakeRandomValueOfMeasurement.randomValue();
        measurementsRegistration.setValue(value);
        boolean isRaining = iMakeRandomIsRainingOfMeasurement.getRandomIsRaining();
        measurementsRegistration.setIsRaining(isRaining);
        String body = "{\"value\":\"" + measurementsRegistration.getValue() + "\",\n" +
                "\"isRaining\":" + measurementsRegistration.getIsRaining() + "}";
        URL url = new URL(urlAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
            dos.writeBytes(body);
            log.info("Sensor with UUID:" + iSensorIsRegistering.getUuid() + " has registered value of temperature: " + value + " and was raining: " + isRaining);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            log.info("Sensor send measuring data on a server");
        } else {
            log.warn("Measurement data hasn't been sending on a server!");
        }
    }
}