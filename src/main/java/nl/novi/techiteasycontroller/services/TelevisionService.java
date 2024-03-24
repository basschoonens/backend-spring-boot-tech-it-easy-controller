package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.TelevisionDtoInput;
import nl.novi.techiteasycontroller.dtos.TelevisionDtoOutput;
import nl.novi.techiteasycontroller.dtos.TelevisionSalesDtoOutput;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.CIModule;
import nl.novi.techiteasycontroller.models.RemoteController;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.CIModuleRepository;
import nl.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;
    private final CIModuleRepository ciModuleRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository, CIModuleRepository ciModuleRepository) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<TelevisionDtoOutput> getTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDtoOutput> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
           TelevisionDtoOutput dto = toDto(television);
            televisionDtos.add(dto);
        }

        return televisionDtos;
    }

    public TelevisionDtoOutput getTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if (optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found.");
        } else {
            return toDto(optionalTelevision.get());
        }
    }

    public void saveTelevision(TelevisionDtoInput dto) {
        Television television = toTelevision(dto);
        televisionRepository.save(television);
    }

    public void updateTelevision(Long id, TelevisionDtoInput dto) {
        Optional<Television> televisionFound = televisionRepository.findById(id);
        if (televisionFound.isEmpty()) {
            throw new RecordNotFoundException("Television with id " + id + " not found");
        }
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
    private TelevisionDtoOutput toDto(Television television) {
        TelevisionDtoOutput dto = new TelevisionDtoOutput();
        dto.setId(  television.getId());
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
        dto.setSold(television.getSold());
        dto.setRemoteid(television.getRemoteController().getId());
        dto.setCimoduleid(television.getCiModule().getId());

        return dto;
    }

    private Television toTelevision(TelevisionDtoInput dto) {
        return toTelevision(dto, new Television());
    }

    private Television toTelevision(TelevisionDtoInput dto, Television television) {
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
        if (dto.getSold() != null){
            television.setSold(dto.getSold());
        }

        return television;
    }

    public TelevisionSalesDtoOutput getSalesInfoById(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        TelevisionSalesDtoOutput tsod = new TelevisionSalesDtoOutput();

        if (optionalTelevision.isPresent()) {
            Television television = optionalTelevision.get();
            tsod.setId(television.getId());
            tsod.setPrice(television.getPrice());
            tsod.setOriginalStock(television.getOriginalStock());
            tsod.setSold(television.getSold());
            tsod.setProfit((int) (television.getSold() * television.getPrice()));
        }

        return tsod;
    }

    public void assignRemoteController(Long televisionid, Long remotecontrollerid) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionid);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remotecontrollerid);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television = optionalTelevision.get();
            RemoteController remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException("De televisie of remote controller kan niet worden gevonden.");
        }
    }

    public void assignCiModule(Long televisionid, Long cimoduleid) {
        Optional<Television> optionalTelevision = televisionRepository.findById(televisionid);
        Optional<CIModule> optionalModule = ciModuleRepository.findById(cimoduleid);

        if (optionalTelevision.isPresent() && optionalModule.isPresent()) {
            Television television = optionalTelevision.get();
            CIModule module = optionalModule.get();

            television.setCiModule(module);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException("De televisie of ci-module kan niet worden gevonden.");
        }
    }


}

