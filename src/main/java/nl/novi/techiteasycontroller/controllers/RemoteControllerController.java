package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    @Autowired
    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

}
