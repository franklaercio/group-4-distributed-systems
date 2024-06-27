package br.ufrn.data.manager.controllers;

import br.ufrn.data.manager.repositories.OpenDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/opendata")
public class OpenDataController {

    private final OpenDataRepository openDataRepository;

    public OpenDataController(OpenDataRepository openDataRepository) {
        this.openDataRepository = openDataRepository;
    }

    @GetMapping
    public ResponseEntity<?> sync(@RequestParam String datasource) {
        return ResponseEntity.ok().body(this.openDataRepository.sync(datasource));
    }
}