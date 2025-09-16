package com.demo.intergration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DemoResponse {
    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("display_name")
    private String displayName;

    private Address address;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        private String road;
        private String suburb;
        private String city;
        private String state;
        private String country;
        private String postcode;
    }
}
