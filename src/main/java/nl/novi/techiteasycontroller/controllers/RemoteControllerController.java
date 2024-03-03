package nl.novi.techiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasycontroller.dtos.RemoteControllerDtoInput;
import nl.novi.techiteasycontroller.dtos.RemoteControllerDtoOutput;
import nl.novi.techiteasycontroller.services.RemoteControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    @Autowired
    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping()
    public ResponseEntity<List<RemoteControllerDtoOutput>> getRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDtoOutput> getRemoteControllerById(@PathVariable Long id) {
        RemoteControllerDtoOutput remote = remoteControllerService.getRemoteController(id);

        return ResponseEntity.ok(remote);
    }

    @PostMapping()
    public ResponseEntity<Void> addRemoteController(@Valid @RequestBody RemoteControllerDtoInput remote) {
        remoteControllerService.saveRemoteController(remote);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRemoteController(@Valid @PathVariable Long id, @RequestBody RemoteControllerDtoInput remote) {
        remoteControllerService.updateRemoteController(id, remote);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

}
