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
        Television television = toTelevision(dto);
        televisionRepository.save(television);
    }

    public void updateTelevision(Long id, InputTelevisionDto dto) {
        Optional<Television> televisionFound = televisionRepository.findById(id);
        televisionRepository.save(toTelevision(dto, televisionFound.get()));
    }

    public void deleteTelevision(Long id) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        } else {
            televisionRepository.deleteById(id);
        }
    }

    // Mappers //
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
        dto.setSmartTV(television.getSmartTV());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());

        return dto;
    }

    private Television toTelevision(InputTelevisionDto dto){
        return toTelevision(dto, new Television());
    }

    private Television toTelevision(InputTelevisionDto dto, Television television)  {
        if (dto.getBrand() != null) {
            television.setBrand(dto.getBrand());
        }
        if (dto.getName() != null) {
            television.setName(dto.getName());
        }
        if (dto.getPrice() != null) {
            television.setPrice(dto.getPrice());
        }
        if (dto.getAvailableSize() != null) {
            television.setAvailableSize(dto.getAvailableSize());
        }
        if (dto.getRefreshRate() != null) {
            television.setRefreshRate(dto.getRefreshRate());
        }
        if (dto.getScreenType() != null){
            television.setScreenType(dto.getScreenType());
        }
        if (dto.getScreenQuality() != null) {
            television.setScreenQuality(dto.getScreenQuality());
        }
        if (dto.getSmartTV() != null) {
            television.setSmartTV(dto.getSmartTV());
        }
        if (dto.getWifi() != null) {
            television.setWifi(dto.getWifi());
        }
        if (dto.getVoiceControl() != null) {
            television.setVoiceControl(dto.getVoiceControl());
        }
        if (dto.getHdr() != null) {
            television.setHdr(dto.getHdr());
        }
        if (dto.getBluetooth() != null) {
            television.setBluetooth(dto.getBluetooth());
        }
        if (dto.getAmbiLight() != null) {
            television.setAmbiLight(dto.getAmbiLight());
        }

        return television;
    }

}

