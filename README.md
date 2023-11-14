# Weather Sensor

**Project goal**: Develop a system for collecting and processing weather data.
The system consists of two parts: a RESTful API service for filtering and
sorting weather data (hereinafter referred to as the “server”), as well as at least one service,
providing weather data (hereinafter referred to as “sensor”).
The sensor is an application that can register itself
on server. Then, at certain intervals, the sensor reports
weather data to the server. The server receives data from the sensor and stores it in
DB. The client can receive data from the server about available sensors and measurements
weather.
 ***               
## Used technologies

* Java 17
* Swagger
* Spring (MVC, Boot, Data Jpa);
* Library Lombok;
* PostgresSQL
* Maven
* JUnit5
* Log4J
***   
## Database

Application uses PostgreSQL database. 
## URL for DB: 
### jdbc:postgresql://localhost:5432/weather_sensor_db 

 Database contains two tables:

* **Table _data_of_weather_** - contains information about measurement which sensor maked:
  * Day, mont and year when data of weather was recorded;
  * Was rain in that time;
  * Time of measurement recording;
  * Value of temperature;
  * Date in milliseconds start with 1 January 1970; 
  * Id of sensor which send that date on a server;
  * Id of measurement;


* **Table _sensor_table_** - contains information about sensor:
  * Id of sensor;
  * Is activated sensor or not;
  * Key of sensor;
  * Name of sensor);
        
***   
## Available endpoints

Available endpoints 
POST endpoints:
http://localhost:8080/sensors/registration - Registering the sensor on the server.

http://localhost:8080/sensors/{key}/measurements - Weather Data Logging
{key} - unique sensor key which autogenerate for every sensor.

GET endpoints:
http://localhost:8080/sensors - Retrieving all active sensors
http://localhost:8080/sensors/{key}/measurements - Retrieving the last 20 sensor measurements
{key} - unique sensor key which autogenerate for every sensor.
http://localhost:8080/sensors/measurements - Request to receive up-to-date information from all sensors.
Measurements whose time does not differ from request time for more than one minute.

***
## Describe working application

Once the application is launched, the sensor is connected using the UUID key and applied to the databases. 
Registration logs or errors saved to the project root directory. Next, the sensor received each 15 seconds new value of the 
measured temperature depending on -100 to 100, and the Boolean value was recorded as either rain or its absence.
Measurement registration data is stored in the source data. You can then execute the endpoints and capture the result of the calls.
All requested endpoint data is provided in JSON format.
***
## Time spent:
Design 2-3 hours,
Implementation 2 days,
Documentation 1 day,
Testing 1 day.