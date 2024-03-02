package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

}
