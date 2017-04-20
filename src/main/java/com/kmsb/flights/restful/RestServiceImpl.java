package com.kmsb.flights.restful;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsb.flights.persistence.entity.FlightStates;
import com.kmsb.flights.service.RestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Repository("restService")
public class RestServiceImpl implements RestService {
    private static final String openSkyURI = "https://opensky-network.org/api/states/all";

    @Cacheable("stateVectors")
    public FlightStates retrieveFlightStates() {

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

    @CacheEvict(value = "stateVectors", allEntries = true)
    public void refreshAllStateVectors() {
    }

}
