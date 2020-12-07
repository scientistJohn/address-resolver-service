package com.andrii.address_resolver.service;

import com.andrii.address_resolver.api.GeoCodeItems;
import com.andrii.address_resolver.config.HereComConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.Optional;

public class HereComRepository {
    private final ResponseHandler<GeoCodeItems> geoCodeItemsResponseHandler;
    private final HttpClient httpClient;
    private final HereComConfiguration hereComConfiguration;

    public HereComRepository(ObjectMapper objectMapper, HttpClient httpClient, HereComConfiguration hereComConfiguration) {
        this.geoCodeItemsResponseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new IOException();
            }
            return objectMapper.readValue(response.getEntity().getContent(), GeoCodeItems.class);
        };
        this.httpClient = httpClient;
        this.hereComConfiguration = hereComConfiguration;
    }

    public Optional<GeoCodeItems> findGeoCodeItems(String query) {
        try {
            HttpGet request = hereComConfiguration.getGeoCodePathRequest(query);
            GeoCodeItems response = httpClient.execute(request, geoCodeItemsResponseHandler);
            return Optional.of(response);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
