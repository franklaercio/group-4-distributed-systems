package br.ufrn.data.manager.controllers;

import br.ufrn.data.manager.repositories.OpenDataRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OpenDataController {

    private final OpenDataRepository openDataRepository;

    public OpenDataController(OpenDataRepository openDataRepository) {
        this.openDataRepository = openDataRepository;
    }

    @PostMapping("/ckan")
    public ResponseEntity<?> syncCkanData() {
        return ResponseEntity.ok().body(this.openDataRepository.syncCkan());
    }

    @PostMapping("/dkan")
    public ResponseEntity<?> syncDkanData() {
        return ResponseEntity.ok().body(this.openDataRepository.syncDkan());
    }

    @PostMapping("/socrata")
    public ResponseEntity<?> syncSocrataData() {
        return ResponseEntity.ok().body(this.openDataRepository.syncSocrata());
    }
}