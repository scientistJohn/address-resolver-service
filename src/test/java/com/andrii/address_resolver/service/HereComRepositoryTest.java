package com.andrii.address_resolver.service;

import com.andrii.address_resolver.api.GeoCodeItems;
import com.andrii.address_resolver.config.HereComConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HereComRepositoryTest {
    private static ObjectMapper objectMapper;
    private static HttpClient httpClient;
    private static HereComConfiguration hereComConfiguration;
    private static HereComRepository hereComRepository;

    @BeforeEach
    public void init(){
        objectMapper = mock(ObjectMapper.class);
        httpClient = mock(HttpClient.class);
        hereComConfiguration = mock(HereComConfiguration.class);
        hereComRepository = new HereComRepository(objectMapper, httpClient, hereComConfiguration);
    }

    @Test
    public void findGeoCodeItemsOK() throws IOException {
        HttpGet request = mock(HttpGet.class);
        when(hereComConfiguration.getGeoCodePathRequest(any())).thenReturn(request);
        GeoCodeItems response = mock(GeoCodeItems.class);
        when(httpClient.execute(any(HttpUriRequest.class), any(ResponseHandler.class))).thenReturn(response);

        Optional<GeoCodeItems> result = hereComRepository.findGeoCodeItems("");

        assertTrue(result.isPresent());
        assertSame(response, result.get());
        verify(hereComConfiguration).getGeoCodePathRequest(eq(""));
        verify(httpClient).execute(eq(request), any(ResponseHandler.class));

    }

    @Test
    public void findGeoCodeItemsIllegalStateException() {
        when(hereComConfiguration.getGeoCodePathRequest(any())).thenThrow(new IllegalStateException());

        assertThrows(IllegalStateException.class, () -> {
            hereComRepository.findGeoCodeItems("");
        });
    }

    @Test
    public void findGeoCodeItemsTestEmpty() throws IOException {
        HttpGet request = mock(HttpGet.class);
        when(hereComConfiguration.getGeoCodePathRequest(any())).thenReturn(request);
        when(httpClient.execute(any(HttpUriRequest.class), any(ResponseHandler.class))).thenThrow(new IOException());

        Optional<GeoCodeItems> result = hereComRepository.findGeoCodeItems("");

        assertFalse(result.isPresent());
        verify(hereComConfiguration).getGeoCodePathRequest(eq(""));
    }
}
