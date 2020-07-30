package com.github.ggwadera.citiesapi.countries.repositories;

import com.github.ggwadera.citiesapi.countries.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
