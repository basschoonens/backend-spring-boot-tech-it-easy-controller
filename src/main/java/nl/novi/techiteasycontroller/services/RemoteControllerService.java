package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.RemoteControllerDtoInput;
import nl.novi.techiteasycontroller.dtos.RemoteControllerDtoOutput;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.RemoteController;
import nl.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDtoOutput> getRemoteControllers() {
        List<RemoteController> remotes = remoteControllerRepository.findAll();
        List<RemoteControllerDtoOutput> remoteDtos = new ArrayList<>();

        for (RemoteController remote : remotes) {
            RemoteControllerDtoOutput dto = toDto(remote);
            remoteDtos.add(dto);
        }

        return remoteDtos;
    }

    public RemoteControllerDtoOutput getRemoteController(Long id) {
        Optional<RemoteController> optionalRemote = remoteControllerRepository.findById(id);
        if (optionalRemote.isEmpty()) {
            throw new RecordNotFoundException("Remote controller with id " + id + " not found");
        } else {
            return toDto(optionalRemote.get());
        }
    }

    public void saveRemoteController(RemoteControllerDtoInput dto) {
        RemoteController remote = toRemote(dto);
        remoteControllerRepository.save(remote);
    }

    public void updateRemoteController(Long id, RemoteControllerDtoInput dto) {
        Optional<RemoteController> remoteFound = remoteControllerRepository.findById(id);
        if (remoteFound.isEmpty()) {
            throw new RecordNotFoundException("Remote Control with id " + id + " is not found");
        }
        remoteControllerRepository.save(toRemote(dto, remoteFound.get()));
    }

    public void deleteRemoteController(Long id) {
        Optional<RemoteController> remote = remoteControllerRepository.findById(id);
        if (remote.isEmpty()) {
            throw new RecordNotFoundException("Remote Control with id " + id + " not found.");
        } else {
            remoteControllerRepository.deleteById(id);
        }
    }

    private RemoteController toRemote(RemoteControllerDtoInput dto) {
        return toRemote(dto, new RemoteController());
    }

    private RemoteController toRemote(RemoteControllerDtoInput dto, RemoteController remote) {
        if (dto.getBrand() != null) {
            remote.setBrand(dto.getBrand());
        }
        if (dto.getName() != null) {
            remote.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            remote.setPrice(dto.getPrice());
        }
        if (dto.getBatteryType() != null) {
            remote.setBatteryType(dto.getBatteryType());
        }
        if (dto.getCompatibleWith() != null) {
            remote.setCompatibleWith(dto.getCompatibleWith());
        }
        if (dto.getOriginalStock() != null) {
            remote.setOriginalStock(dto.getOriginalStock());
        }

        return remote;
    }

    private RemoteControllerDtoOutput toDto(RemoteController remote) {
        RemoteControllerDtoOutput dto = new RemoteControllerDtoOutput();
        dto.setId(remote.getId());
        dto.setBrand(remote.getBrand());
        dto.setName(remote.getName());
        dto.setPrice(remote.getPrice());
        dto.setBatteryType(remote.getBatteryType());
        dto.setCompatibleWith(remote.getCompatibleWith());
        dto.setOriginalStock(remote.getOriginalStock());

        return dto;
    }



}
