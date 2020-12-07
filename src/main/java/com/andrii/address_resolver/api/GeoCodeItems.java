package com.andrii.address_resolver.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class GeoCodeItems {
    private final List<Item> items;

    public GeoCodeItems(@JsonProperty("items") List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public static class Item {
        private final String title;
        private final String id;
        private final String resultType;
        private final String houseNumberType;
        private final Address address;
        private final Position position;
        private final List<Position> access;
        private final MapView mapView;
        private final Scoring scoring;

        public Item(@JsonProperty("title") String title,
                    @JsonProperty("id") String id,
                    @JsonProperty("resultType") String resultType,
                    @JsonProperty("houseNumberType") String houseNumberType,
                    @JsonProperty("address") Address address,
                    @JsonProperty("position") Position position,
                    @JsonProperty("access") List<Position> access,
                    @JsonProperty("mapView") MapView mapView,
                    @JsonProperty("scoring") Scoring scoring) {
            this.title = title;
            this.id = id;
            this.resultType = resultType;
            this.houseNumberType = houseNumberType;
            this.address = address;
            this.position = position;
            this.access = access;
            this.mapView = mapView;
            this.scoring = scoring;
        }

        public String getTitle() {
            return title;
        }

        public String getId() {
            return id;
        }

        public String getResultType() {
            return resultType;
        }

        public String getHouseNumberType() {
            return houseNumberType;
        }

        public Address getAddress() {
            return address;
        }

        public Position getPosition() {
            return position;
        }

        public List<Position> getAccess() {
            return access;
        }

        public MapView getMapView() {
            return mapView;
        }

        public Scoring getScoring() {
            return scoring;
        }
    }

    public static class Address {
        private final String label;
        private final String countryCode;
        private final String countryName;
        private final String stateCode;
        private final String state;
        private final String countyCode;
        private final String county;
        private final String city;
        private final String district;
        private final String street;
        private final String postalCode;
        private final String houseNumber;

        public Address(@JsonProperty("label") String label,
                       @JsonProperty("countryCode") String countryCode,
                       @JsonProperty("countryName") String countryName,
                       @JsonProperty("stateCode") String stateCode,
                       @JsonProperty("state") String state,
                       @JsonProperty("countyCode") String countyCode,
                       @JsonProperty("county") String county,
                       @JsonProperty("city") String city,
                       @JsonProperty("district") String district,
                       @JsonProperty("street") String street,
                       @JsonProperty("postalCode") String postalCode,
                       @JsonProperty("houseNumber") String houseNumber) {
            this.label = label;
            this.countryCode = countryCode;
            this.countryName = countryName;
            this.stateCode = stateCode;
            this.state = state;
            this.countyCode = countyCode;
            this.county = county;
            this.city = city;
            this.district = district;
            this.street = street;
            this.postalCode = postalCode;
            this.houseNumber = houseNumber;
        }

        public String getLabel() {
            return label;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public String getStateCode() {
            return stateCode;
        }

        public String getState() {
            return state;
        }

        public String getCountyCode() {
            return countyCode;
        }

        public String getCounty() {
            return county;
        }

        public String getCity() {
            return city;
        }

        public String getDistrict() {
            return district;
        }

        public String getStreet() {
            return street;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address address = (Address) o;
            return Objects.equals(label, address.label) &&
                    Objects.equals(countryCode, address.countryCode) &&
                    Objects.equals(countryName, address.countryName) &&
                    Objects.equals(stateCode, address.stateCode) &&
                    Objects.equals(state, address.state) &&
                    Objects.equals(countyCode, address.countyCode) &&
                    Objects.equals(county, address.county) &&
                    Objects.equals(city, address.city) &&
                    Objects.equals(district, address.district) &&
                    Objects.equals(street, address.street) &&
                    Objects.equals(postalCode, address.postalCode) &&
                    Objects.equals(houseNumber, address.houseNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label, countryCode, countryName, stateCode, state, countyCode, county, city, district, street, postalCode, houseNumber);
        }
    }

    public static class Position {
        private final Double lat;
        private final Double lng;

        public Position(@JsonProperty("lat") Double lat, @JsonProperty("lng") Double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public Double getLng() {
            return lng;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(lat, position.lat) &&
                    Objects.equals(lng, position.lng);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lat, lng);
        }
    }

    public static class MapView {
        private final Double west;
        private final Double south;
        private final Double east;
        private final Double north;

        public MapView(@JsonProperty("west") Double west,
                       @JsonProperty("south") Double south,
                       @JsonProperty("east") Double east,
                       @JsonProperty("north") Double north) {
            this.west = west;
            this.south = south;
            this.east = east;
            this.north = north;
        }

        public Double getWest() {
            return west;
        }

        public Double getSouth() {
            return south;
        }

        public Double getEast() {
            return east;
        }

        public Double getNorth() {
            return north;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapView mapView = (MapView) o;
            return Objects.equals(west, mapView.west) &&
                    Objects.equals(south, mapView.south) &&
                    Objects.equals(east, mapView.east) &&
                    Objects.equals(north, mapView.north);
        }

        @Override
        public int hashCode() {
            return Objects.hash(west, south, east, north);
        }
    }

    public static class FieldScore {
        private final Double city;
        private final List<Double> streets;
        private final Double houseNumber;

        public FieldScore(@JsonProperty("city") Double city,
                          @JsonProperty("streets") List<Double> streets,
                          @JsonProperty("houseNumber") Double houseNumber) {
            this.city = city;
            this.streets = streets;
            this.houseNumber = houseNumber;
        }

        public Double getCity() {
            return city;
        }

        public List<Double> getStreets() {
            return streets;
        }

        public Double getHouseNumber() {
            return houseNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FieldScore that = (FieldScore) o;
            return Objects.equals(city, that.city) &&
                    Objects.equals(streets, that.streets) &&
                    Objects.equals(houseNumber, that.houseNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(city, streets, houseNumber);
        }
    }

    public static class Scoring {
        private final Double queryScore;
        private final FieldScore fieldScore;

        public Scoring(@JsonProperty("queryScore") Double queryScore, @JsonProperty("fieldScore") FieldScore fieldScore) {
            this.queryScore = queryScore;
            this.fieldScore = fieldScore;
        }

        public Double getQueryScore() {
            return queryScore;
        }

        public FieldScore getFieldScore() {
            return fieldScore;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Scoring scoring = (Scoring) o;
            return Objects.equals(queryScore, scoring.queryScore) &&
                    Objects.equals(fieldScore, scoring.fieldScore);
        }

        @Override
        public int hashCode() {
            return Objects.hash(queryScore, fieldScore);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoCodeItems that = (GeoCodeItems) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
