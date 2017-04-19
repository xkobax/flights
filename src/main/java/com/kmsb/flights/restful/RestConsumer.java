package com.kmsb.flights.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsb.flights.persistence.entity.FlightStates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestConsumer {
    private static final String openSkyURI = "https://opensky-network.org/api/states/all";


    public static FlightStates retrieveFlightStates() {

        FlightStates flightStates = null;

        try {

            URL url = new URL(openSkyURI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output = br.readLine();
            conn.disconnect();
            ObjectMapper mapper = new ObjectMapper();
            flightStates = mapper.readValue(output, FlightStates.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return flightStates;
    }


}
