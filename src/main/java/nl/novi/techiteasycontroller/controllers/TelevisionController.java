package nl.novi.techiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasycontroller.dtos.TelevisionDtoInput;
import nl.novi.techiteasycontroller.dtos.TelevisionDtoOutput;
import nl.novi.techiteasycontroller.dtos.TelevisionSalesDtoOutput;
import nl.novi.techiteasycontroller.services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping()
    public ResponseEntity<List<TelevisionDtoOutput>> getTelevisions() {
        return ResponseEntity.ok(televisionService.getTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDtoOutput> getTelevisionById(@PathVariable Long id) {
        TelevisionDtoOutput television = televisionService.getTelevision(id);

        return ResponseEntity.ok(television);
    }

    @PostMapping()
    public ResponseEntity<Void> addTelevision(@Valid @RequestBody TelevisionDtoInput television) {
        televisionService.saveTelevision(television);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTelevision(@Valid @PathVariable Long id, @RequestBody TelevisionDtoInput television) {
        televisionService.updateTelevision(id, television);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<TelevisionSalesDtoOutput> getSalesTelevisionInfo(@PathVariable Long id) {
        TelevisionSalesDtoOutput tsod = televisionService.getSalesInfoById(id);
        return ResponseEntity.ok(tsod);
    }

    @PutMapping("/{televisionid}/remotecontrollers/{remotecontrollerid}")
    public ResponseEntity<Void> assignRemoteController(@PathVariable Long televisionid, @PathVariable Long remotecontrollerid) {
        televisionService.assignRemoteController(televisionid, remotecontrollerid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{televisionid}/cimodules/{cimoduleid}")
    public ResponseEntity<Void> assignCiModule(@PathVariable Long televisionid, @PathVariable Long cimoduleid) {
        televisionService.assignCiModule(televisionid, cimoduleid);
        return ResponseEntity.noContent().build();
    }

}