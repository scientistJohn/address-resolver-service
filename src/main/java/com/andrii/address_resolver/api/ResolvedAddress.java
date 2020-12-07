package com.andrii.address_resolver.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ResolvedAddress {
    private final String normalized;
    private final String status;
    private final int hereUsage;

    @JsonCreator
    public ResolvedAddress(@JsonProperty("normalized") String normalized,
                           @JsonProperty("status") String status,
                           @JsonProperty("hereUsage") int hereUsage) {
        this.normalized = normalized;
        this.status = status;
        this.hereUsage = hereUsage;
    }

    public ResolvedAddress(String status,
                           int hereUsage) {
        this.normalized = null;
        this.status = status;
        this.hereUsage = hereUsage;
    }

    @JsonProperty("normalized")
    public String getNormalized() {
        return normalized;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("hereUsage")
    public int getHereUsage() {
        return hereUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolvedAddress that = (ResolvedAddress) o;
        return hereUsage == that.hereUsage &&
                normalized.equals(that.normalized) &&
                status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalized, status, hereUsage);
    }
}
