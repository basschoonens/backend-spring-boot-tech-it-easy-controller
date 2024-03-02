package nl.novi.techiteasycontroller.services;

import nl.novi.techiteasycontroller.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

}
