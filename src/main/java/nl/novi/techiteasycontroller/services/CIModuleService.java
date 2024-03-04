package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.dtos.CIModuleDtoInput;
import nl.novi.techiteasycontroller.dtos.CIModuleDtoOutput;
import nl.novi.techiteasycontroller.models.CIModule;
import nl.novi.techiteasycontroller.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public static CIModuleDtoOutput getCiModule(Long id) {
    }

    public void saveCiModule(CIModuleDtoInput module) {
    }

    public void updateCiModule(Long id, CIModuleDtoInput module) {
    }

    public void deleteCiModule(Long id) {
    }

    private CIModuleDtoOutput toDto(CIModule module) {
        CIModuleDtoOutput dto = new CIModuleDtoOutput();
        dto.setId(module.getId());
        dto.setName(module.getName());
        dto.setType(module.getType());
        dto.setPrice(module.getPrice());

        return dto;
    }

}
