package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService, TelevisionRepository televisionRepository) {
        this.televisionService = televisionService;
    }

    // Get request to retrieve all televisions
    // Get request to retrieve a single television
    // Post request to add a new television
    // Put request to update an existing television
    // Delete request to delete an existing television

    @GetMapping()
    public ResponseEntity<List<Television>> getTelevisions() {
        return ResponseEntity.ok(televisionService.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable Long id) {
        Television television = televisionService.getTelevision(id);

        return ResponseEntity.ok(television);
    }

    //GET BY BRAND OR BY NAME EXERCISE//

    @GetMapping
    public ResponseEntity<List<Television>> getaTelevisionByBrand(@RequestParam(name = "brand", required = false) String brand, @RequestParam(name = "name", required = false) String name){
        List<Television> televisions;
        if (brand != null && name != null){
            throw new RecordNotFoundException("Provide either brand or name");
        } else if (brand != null){
            televisions = televisionRepository.findByBrand(brand);
        } else if (name != null){
            televisions = televisionRepository.findByName(name);
        } else {
            televisions = televisionRepository.findAll();
        }
        return ResponseEntity.ok(televisions);
    }

    //GET BY BRAND OR BY NAME EXERCISE//

    @PostMapping()
    public ResponseEntity<Void> addTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}