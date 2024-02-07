package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/televisions")
public class TelevisionController {

    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }


    // Get request to retrieve all televisions
    // Get request to retrieve a single television
    // Post request to add a new television
    // Put request to update an existing television
    // Delete request to delete an existing television


    @GetMapping()
    public ResponseEntity<List<Television>> getTelevisions() {
        return ResponseEntity.ok(televisionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()){
            throw new RecordNotFoundException("This record is not available");
        }
        return ResponseEntity.ok(optionalTelevision.get());
    }

    @PostMapping()
    public ResponseEntity<Void> addTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable Long id, @RequestBody String television) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
