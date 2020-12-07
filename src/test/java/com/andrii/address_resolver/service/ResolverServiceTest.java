package com.andrii.address_resolver.service;

import com.andrii.address_resolver.api.Address;
import com.andrii.address_resolver.api.GeoCodeItems;
import com.andrii.address_resolver.api.ResolvedAddress;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResolverServiceTest {
    private static HereComRepository repository;
    private static ResolverService service;

    @BeforeEach
    public void init() {
        repository = mock(HereComRepository.class);
        service = new ResolverService(repository);
    }

    @Test
    public void resolveAddressInsufficientLocationInput() {
        Address address = new Address("", "", "", "", "");
        ResolvedAddress result = service.resolveAddress(address);
        assertEquals("Insufficient Location Input", result.getStatus());
        assertEquals(0, result.getHereUsage());
    }

    @Test
    public void resolveAddressInvalidPOBox() {
        Address address = new Address("UKR", "", "KYIV", "PO Box", "");
        ResolvedAddress result = service.resolveAddress(address);
        assertEquals("Invalid", result.getStatus());
        assertEquals(0, result.getHereUsage());

        address = new Address("UKR", "", "KYIV", "Mailbox", "");
        result = service.resolveAddress(address);
        assertEquals("Invalid", result.getStatus());
        assertEquals(0, result.getHereUsage());

        address = new Address("UKR", "", "KYIV", "Locked Bag", "");
        result = service.resolveAddress(address);
        assertEquals("Invalid", result.getStatus());
        assertEquals(0, result.getHereUsage());
    }

    @Test
    public void resolveAddressInvalidEmptyResponse() {
        when(repository.findGeoCodeItems(any())).thenReturn(Optional.empty());
        Address address = new Address("UKR", "KYIV", "KYIV", "Rybalko st. 5", null);

        ResolvedAddress result = service.resolveAddress(address);

        assertEquals("Unknown", result.getStatus());
        assertEquals(1, result.getHereUsage());
    }

    @Test
    public void resolveAddressInvalidEmptyLabelAndHouseNumber() {
        GeoCodeItems.Item item = new GeoCodeItems.Item("", "", "", "",
                new GeoCodeItems.Address("", "", "", "", "", "", "", "", "", "", "", ""), null, ImmutableList.of(), null, null);
        GeoCodeItems geoCodeItems = new GeoCodeItems(ImmutableList.of(item));
        when(repository.findGeoCodeItems(any())).thenReturn(Optional.of(geoCodeItems));
        Address address = new Address("UKR", "KYIV", "KYIV", "Rybalko st. 5", null);

        ResolvedAddress result = service.resolveAddress(address);

        assertEquals("Invalid", result.getStatus());
        assertEquals(1, result.getHereUsage());
    }

    @Test
    public void resolveAddressOK() {
        GeoCodeItems.Item item = new GeoCodeItems.Item("", "", "", "",
                new GeoCodeItems.Address("label", "", "", "", "", "", "", "", "", "", "", "1"), null, ImmutableList.of(), null, null);
        GeoCodeItems geoCodeItems = new GeoCodeItems(ImmutableList.of(item));
        when(repository.findGeoCodeItems(any())).thenReturn(Optional.of(geoCodeItems));
        Address address = new Address("UKR", "KYIV", "KYIV", "Rybalko st. 5", null);

        ResolvedAddress result = service.resolveAddress(address);

        assertEquals("Valid", result.getStatus());
        assertEquals(1, result.getHereUsage());
        assertEquals("label", result.getNormalized());
    }
}
