package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.services.CIModuleService;
import nl.novi.techiteasycontroller.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;

    @Autowired
    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }


}
