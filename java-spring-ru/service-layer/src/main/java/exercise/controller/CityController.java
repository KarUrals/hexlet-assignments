package exercise.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public ResponseEntity<String> getCityWeather(@PathVariable long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        String cityName = city.getName();
        String respons = weatherService.getWeatherInCity(cityName);

        final HttpHeaders httpHeaders= new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(respons, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/search")
    public List<Map<String, String>> getCities(@RequestParam(defaultValue = "test") String name) {
        List<City> cities = new ArrayList<>();
        if (name.equals("test")) {
            cities = cityRepository.findByOrderByNameAsc();
        } else {
            cities = cityRepository.findByNameStartingWithIgnoreCase(name);
        }

        List<City> filteredCities = cityRepository.findByNameStartingWithIgnoreCase(name);
        List<City> orderedCities = cityRepository.findByOrderByNameAsc();
//        Map<String, String> filteredCities = cities.stream()
//        List<City> orderedCities = cityRepository.findByOrderByNameAsc();
//        if (name == null) {
//            return orderedCities;
//        } else {
//            return filteredCities;
//        }

        List<Map<String, String>> rList = new ArrayList<>();
        for (City city : cities) {
            Map<String, String> rMap = new HashMap<>();
            String cityName = city.getName();
            String info = weatherService.getWeatherInCity(cityName);

            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> weather = new HashMap<>();
            try {
                weather = mapper.readValue(info, Map.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            String temp = weather.get("temperature");
            rMap.put("temperature", temp);
            rMap.put("name", cityName);

            rList.add(rMap);
        }

        return rList;
    }

    @GetMapping(path = "/1")
    public List<City> get() {
        List<City> orderedCities = cityRepository.findByOrderByNameAsc();

        return orderedCities;
    }
    // END
}

