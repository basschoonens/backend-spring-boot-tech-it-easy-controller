package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.InputTelevisionDto;
import nl.novi.techiteasycontroller.dtos.OutputTelevisionDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {


    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<OutputTelevisionDto> getTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<OutputTelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
           OutputTelevisionDto dto = toDto(television);
            televisionDtos.add(dto);
        }

        return televisionDtos;
    }

    public OutputTelevisionDto getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        } else {
            return toDto(optionalTelevision.get());
        }
    }

    public void saveTelevision(InputTelevisionDto dto) {
        Television television = new Television();
        television.setId(dto.getId());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        //TODO aanvullen television properties
        televisionRepository.save(television);
    }

    public void updateTelevision(Long id, InputTelevisionDto television) {
        Optional<Television> televisionFound = televisionRepository.findById(id);
        Television televisionToUpdate;
        if (televisionFound.isPresent()) {
            televisionToUpdate = televisionFound.get();
            televisionToUpdate.setBrand(television.getBrand());
            televisionToUpdate.setName(television.getName());
            televisionToUpdate.setPrice(television.getPrice());
            televisionToUpdate.setAvailableSize(television.getAvailableSize());
            televisionToUpdate.setRefreshRate(television.getRefreshRate());
            televisionToUpdate.setScreenType(television.getScreenType());
            televisionToUpdate.setScreenQuality(television.getScreenQuality());
            televisionToUpdate.setSmartTV(television.getSmartTV());
            televisionToUpdate.setWifi(television.getWifi());
            televisionToUpdate.setVoiceControl(television.getVoiceControl());
            televisionToUpdate.setHdr(television.getHdr());
            televisionToUpdate.setBluetooth(television.getBluetooth());
            televisionToUpdate.setAmbiLight(television.getAmbiLight());

            televisionRepository.save(televisionToUpdate);
        } else {
            throw new RecordNotFoundException("Cannot find television");
        }
        televisionRepository.save(televisionToUpdate);
    }

    // Mapper
    private OutputTelevisionDto toDto(Television television) {
        OutputTelevisionDto dto = new OutputTelevisionDto();
        dto.setId(television.getId());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTV(television.getSmartTV());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());

        return dto;
    }

}

