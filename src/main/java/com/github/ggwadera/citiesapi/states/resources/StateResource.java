package com.github.ggwadera.citiesapi.states.resources;

import com.github.ggwadera.citiesapi.states.entities.State;
import com.github.ggwadera.citiesapi.states.repositories.StateRepository;
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
@RequestMapping("/states")
public class StateResource {

    @Autowired
    private StateRepository repository;

    @GetMapping
    public Page<State> states(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        final Optional<State> state = repository.findById(id);
        if (state.isPresent()) {
            return ResponseEntity.ok().body(state.get());
        }
        return ResponseEntity.notFound().build();
    }
}
