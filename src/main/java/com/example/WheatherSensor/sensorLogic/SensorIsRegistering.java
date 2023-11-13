package com.example.WheatherSensor.sensorLogic;

import com.example.WheatherSensor.registration.SensorRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class that simulates the operation of a sensor when registering on a server and sending a request to the server for a specific
 **/
@Component
public class SensorIsRegistering {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String uuid = "";
    private final String urlAdress = "http://localhost:8080/sensors/registration";

    public String getUuid() {
        return this.uuid;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = 60_000L)
    public void voice() throws IOException {
        SensorRegistration sensorRegistration = new SensorRegistration();
        sensorRegistration.setName("TestSensor");
        String body = "{\"name\": \"" + sensorRegistration.getName() + "\"}";
        URL url = new URL(urlAdress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
            dos.writeBytes(body);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                uuid = line;
                log.info("Registered sensor key: "+line);
            }
        }
    }
}