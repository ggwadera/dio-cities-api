package com.github.ggwadera.citiesapi.states.repositories;

import com.github.ggwadera.citiesapi.states.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
