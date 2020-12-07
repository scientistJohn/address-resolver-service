package com.andrii.address_resolver.service;

import com.andrii.address_resolver.api.Address;
import com.andrii.address_resolver.api.GeoCodeItems;
import com.andrii.address_resolver.api.ResolvedAddress;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class ResolverService {
    private final HereComRepository hereComRepository;

    public ResolverService(HereComRepository hereComRepository) {
        this.hereComRepository = hereComRepository;
    }

    public ResolvedAddress resolveAddress(Address address) {
        if (StringUtils.isAnyBlank(address.getCountry(), address.getCity(), address.getStreet())) {
            return new ResolvedAddress("Insufficient Location Input", 0);
        }

        if (StringUtils.containsIgnoreCase(address.getStreet(), "PO Box")
                || StringUtils.containsIgnoreCase(address.getStreet(), "Mailbox")
                || StringUtils.containsIgnoreCase(address.getStreet(), "Locked Bag")) {
            return new ResolvedAddress("Invalid", 0);
        }

        Optional<GeoCodeItems> response = hereComRepository.findGeoCodeItems(address.toString());

        if (!response.isPresent()) {
            return new ResolvedAddress("Unknown", 1);
        }

        return response.get()
                .getItems()
                .stream()
                .map(GeoCodeItems.Item::getAddress)
                .filter(responseAddress -> !StringUtils.isAnyBlank(responseAddress.getHouseNumber(), responseAddress.getLabel()))
                .findFirst()
                .map(responseAddress -> new ResolvedAddress(responseAddress.getLabel(), "Valid", 1))
                .orElse(new ResolvedAddress("Invalid", 1));
    }
}
