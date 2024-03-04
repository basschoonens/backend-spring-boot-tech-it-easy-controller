package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.CIModuleDtoInput;
import nl.novi.techiteasycontroller.dtos.CIModuleDtoOutput;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.CIModule;
import nl.novi.techiteasycontroller.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModuleDtoOutput> getCiModules() {
        List<CIModule> modules = ciModuleRepository.findAll();
        List<CIModuleDtoOutput> moduleDtos = new ArrayList<>();

        for (CIModule module : modules) {
            CIModuleDtoOutput dto = toDto(module);
            moduleDtos.add(dto);
        }

        return moduleDtos;
    }

    public CIModuleDtoOutput getCiModule(Long id) {
        Optional<CIModule> optionalModule = ciModuleRepository.findById(id);
        if (optionalModule.isEmpty()) {
            throw new RecordNotFoundException("CI-Module with id " + id + " cannot be found.");
        } else {
            return toDto(optionalModule.get());
        }
    }

    public void saveCiModule(CIModuleDtoInput dto) {
        CIModule module = toCiModule(dto);
        ciModuleRepository.save(module);
    }

    public void updateCiModule(Long id, CIModuleDtoInput dto) {
        Optional<CIModule> moduleFound = ciModuleRepository.findById(id);
        if (moduleFound.isEmpty()) {
            throw new RecordNotFoundException("CI-Module with id " + id + " not found");
        }
        ciModuleRepository.save(toCiModule(dto, moduleFound.get()));
    }

    public void deleteCiModule(Long id) {
        Optional<CIModule> module = ciModuleRepository.findById(id);
        if (module.isEmpty()) {
            throw new RecordNotFoundException("CI-Module with id " + id + " not found");
        } else {
            ciModuleRepository.deleteById(id);
        }
    }

    private CIModuleDtoOutput toDto(CIModule module) {
        CIModuleDtoOutput dto = new CIModuleDtoOutput();
        dto.setId(module.getId());
        dto.setName(module.getName());
        dto.setType(module.getType());
        dto.setPrice(module.getPrice());

        return dto;
    }

    private CIModule toCiModule(CIModuleDtoInput dto) {
        return toCiModule(dto, new CIModule());
    }

    private CIModule toCiModule(CIModuleDtoInput dto, CIModule module) {
        if (dto.getName() != null) {
            module.setName(dto.getName());
        }
        if (dto.getType() != null) {
            module.setType(dto.getType());
        }
        if (dto.getPrice() != null) {
            module.setPrice(dto.getPrice());
        }

        return module;
    }
}
