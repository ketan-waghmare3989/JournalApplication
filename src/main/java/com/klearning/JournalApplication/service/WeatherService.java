package com.klearning.JournalApplication.service;

import com.klearning.JournalApplication.api.response.WeatherResponse;
import com.klearning.JournalApplication.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

//    private String apiKey = "d2fbeb7fb5e58e1670f87c04c1bc0acc";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>",apiKey);
            // REST Template to call external API
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_",body,300l);
            }
            return body;
        }

    }


}
