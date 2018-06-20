package com.ticketmonster.movie_beast._initialization;

import com.ticketmonster.movie_beast.models.City;
import com.ticketmonster.movie_beast.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CityInitialization {

    List<String> cities = new ArrayList<String>(Arrays.asList("Athina", "Piraias", "Peristeri", "Kallithea", "Nikaia", "Keratsini",
            "Ilion", "Gliphada", "Zographos", "Ilioupoli", "Aigaleo", "Nea Smirni", "Khalandri", "Amarousion", "Koridallos", "Nea Ionia",
            "Agios Dimitrios", "Palaio Phaliro", "Kiphisia", "Vironas"));

    @Autowired
    CityRepository cityRepository;

    @PostConstruct
    @Transactional
    public void init() {
        IntStream.range(0, 20).forEach((i -> {
            City city = new City();
            city.setCity_name(cities.get(i));
            cityRepository.save(city);
        }));
    }
}