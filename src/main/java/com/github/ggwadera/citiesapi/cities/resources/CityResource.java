package com.github.ggwadera.citiesapi.cities.resources;

import com.github.ggwadera.citiesapi.cities.entities.City;
import com.github.ggwadera.citiesapi.cities.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityResource {

    @Autowired
    private CityRepository repository;

    @GetMapping
    public Page<City> cities(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        final Optional<City> city = repository.findById(id);
        if (city.isPresent()) {
            return ResponseEntity.ok().body(city.get());
        }
        return ResponseEntity.notFound().build();
    }
}
