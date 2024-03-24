package nl.novi.techiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasycontroller.dtos.CIModuleDtoInput;
import nl.novi.techiteasycontroller.dtos.CIModuleDtoOutput;
import nl.novi.techiteasycontroller.services.CIModuleService;
import nl.novi.techiteasycontroller.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping()
    public ResponseEntity<List<CIModuleDtoOutput>> getCiModules() {
        return ResponseEntity.ok(ciModuleService.getCiModules());
    }

    @GetMapping("/{id}0")
    public ResponseEntity<CIModuleDtoOutput> getCiModuleById(@PathVariable Long id) {
        CIModuleDtoOutput module = ciModuleService.getCiModule(id);

        return ResponseEntity.ok(module);
    }

    @PostMapping()
    public ResponseEntity<Void> addCiModule(@Valid @RequestBody CIModuleDtoInput module) {
        ciModuleService.saveCiModule(module);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
        public ResponseEntity<Void> updateCiModule(@Valid @PathVariable Long id, @RequestBody CIModuleDtoInput module) {
        ciModuleService.updateCiModule(id, module);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiModule(@PathVariable Long id) {
        ciModuleService.deleteCiModule(id);
        return ResponseEntity.noContent().build();
    }

}
