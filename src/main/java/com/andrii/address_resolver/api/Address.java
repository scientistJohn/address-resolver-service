package com.andrii.address_resolver.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Address {
    private final String country;
    private final String state;
    private final String city;
    private final String street;
    private final String postalCode;

    @JsonCreator
    public Address(@JsonProperty("country") String country,
                   @JsonProperty("state") String state,
                   @JsonProperty("city") String city,
                   @JsonProperty("street") String street,
                   @JsonProperty("postalCode") String postalCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return street
                + " " + city
                + " " + state
                + " " + country
                + " " + postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(state, address.state) &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, city, street, postalCode);
    }
}
