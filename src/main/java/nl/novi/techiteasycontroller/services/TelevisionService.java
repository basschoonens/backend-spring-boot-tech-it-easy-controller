package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.RequestTelevisionDto;
import nl.novi.techiteasycontroller.dtos.ResponseTelevisionDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ResponseTelevisionDto> getTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<ResponseTelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
           ResponseTelevisionDto dto = toDto(television);
            televisionDtos.add(dto);
        }

        return televisionDtos;
    }

    public ResponseTelevisionDto getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        } else {
            return toDto(optionalTelevision.get());
        }
    }

    public void saveTelevision(RequestTelevisionDto dto) {
        Television television = new Television();
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        //TODO aanvullen television properties
        televisionRepository.save(television);
    }

    // Mapper
    private ResponseTelevisionDto toDto(Television television) {
        ResponseTelevisionDto dto = new ResponseTelevisionDto();
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());

        //TODO waardes aanvulen
        return dto;
    }

}

