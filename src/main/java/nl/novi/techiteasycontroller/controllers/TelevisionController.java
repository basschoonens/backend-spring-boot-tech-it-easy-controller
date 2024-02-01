package nl.novi.techiteasycontroller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelevisionController {

    // Get request to retrieve all televisions
    // Get request to retrieve a single television
    // Post request to add a new television
    // Put request to update an existing television
    // Delete request to delete an existing television

    @GetMapping("api/televisions") // Get request to retrieve all televisions
    public ResponseEntity<String> getTelevisions() {
        return new ResponseEntity<>()
    }

    @GetMapping("api/televisions/{id}") // Get request to retrieve a single television
    public String getTelevisionById(@PathVariable String id) {
        return "Single television";
    }


}
