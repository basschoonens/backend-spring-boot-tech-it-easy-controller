package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

}
