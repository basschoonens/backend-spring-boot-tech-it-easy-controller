package nl.novi.techiteasycontroller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/televisions")
public class TelevisionController {

    // Get request to retrieve all televisions
    // Get request to retrieve a single television
    // Post request to add a new television
    // Put request to update an existing television
    // Delete request to delete an existing television


    @GetMapping()
    public ResponseEntity<String> getTelevisions() {
        return ResponseEntity.ok("television");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevisionById(@PathVariable Long id) {
        return ResponseEntity.ok("Television with id " + id + " retrieved");
    }

    @PostMapping()
    public ResponseEntity<Void> addTelevision(@RequestBody String television) {
        System.out.println(television);
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
