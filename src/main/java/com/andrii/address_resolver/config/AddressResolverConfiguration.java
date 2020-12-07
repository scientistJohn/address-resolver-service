package com.andrii.address_resolver.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AddressResolverConfiguration extends Configuration {
    private final HttpClientConfiguration httpClient;
    private final HereComConfiguration hereCom;

    @JsonCreator
    public AddressResolverConfiguration(@JsonProperty("httpClient") @Valid @NotNull HttpClientConfiguration httpClient,
                                        @JsonProperty("hereCom") @Valid @NotNull  HereComConfiguration hereCom) {
        this.httpClient = httpClient;
        this.hereCom = hereCom;
    }

    public HttpClientConfiguration getHttpClient() {
        return httpClient;
    }

    public HereComConfiguration getHereCom() {
        return hereCom;
    }
}
