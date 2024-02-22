package nl.novi.techiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasycontroller.dtos.InputTelevisionDto;
import nl.novi.techiteasycontroller.dtos.OutputTelevisionDto;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService, TelevisionRepository televisionRepository) {
        this.televisionService = televisionService;
    }

    @GetMapping()
    public ResponseEntity<List<OutputTelevisionDto>> getTelevisions() {
        return ResponseEntity.ok(televisionService.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputTelevisionDto> getTelevisionById(@PathVariable Long id) {
        OutputTelevisionDto television = televisionService.getTelevision(id);

        return ResponseEntity.ok(television);
    }

    @PostMapping()
    public ResponseEntity<Void> addTelevision(@Valid @RequestBody InputTelevisionDto television) {
        televisionService.saveTelevision(television);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTelevision(@PathVariable Long id, @RequestBody InputTelevisionDto television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}