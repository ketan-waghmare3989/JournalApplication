package com.klearning.JournalApplication.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{
    private Current current;


    @Getter
    @Setter
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_icons")
        private List<String> weatherIcons;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_degree")
        private int windDegree;
        @JsonProperty("wind_dir")
        private String windDir;
        private int pressure;
        private double precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        @JsonProperty("uv_index")
        private int uvIndex;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;
    }

}






