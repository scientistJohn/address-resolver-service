package com.andrii.address_resolver.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.http.client.methods.HttpGet;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

public class HereComConfiguration {
    private final String domainName;
    private final String geoCodePath;
    private final String apiKey;
    private final String type;

    @JsonCreator
    public HereComConfiguration(@JsonProperty("domainName") @NotNull String domainName,
                                @JsonProperty("geoCodePath") @NotNull String geoCodePath,
                                @JsonProperty("apiKey") @NotNull String apiKey,
                                @JsonProperty("type") @NotNull String type) {
        this.domainName = domainName;
        this.geoCodePath = geoCodePath;
        this.apiKey = apiKey;
        this.type = type;
    }

    public HttpGet getGeoCodePathRequest(String address) {
        try {
            URI uri = new URI(type,
                    domainName,
                    String.join("/", geoCodePath.split("/")),
                    String.format("q=%s&apiKey=%s", address, apiKey),
                    null);
            return new HttpGet(uri);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Can't build url! Check the property file!");
        }
    }
}
