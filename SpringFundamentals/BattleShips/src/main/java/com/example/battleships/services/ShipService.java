package com.example.battleships.services;

import com.example.battleships.model.Category;
import com.example.battleships.model.Ship;
import com.example.battleships.model.User;
import com.example.battleships.model.dto.CreateShipDTO;
import com.example.battleships.model.dto.FireFxDTO;
import com.example.battleships.model.enums.ShipTypeEnum;
import com.example.battleships.repositories.CategoryRepository;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final LoggedUser loggedUser;
    private final AuthService userService;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ShipService(ShipRepository shipRepository, LoggedUser loggedUser, AuthService userService, CategoryRepository categoryRepository) {
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }


    public boolean createShip(CreateShipDTO createShipDTO) {

        Optional<Ship> shipOptional = this.shipRepository.findByName(createShipDTO.getName());

        if (shipOptional.isPresent()) {
            return false;
        }

        ShipTypeEnum type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipTypeEnum.BATTLE;
            case 1 -> ShipTypeEnum.CARGO;
            case 2 -> ShipTypeEnum.PATROL;
            default -> ShipTypeEnum.BATTLE;
        };

        Category category = this.categoryRepository.findByName(type);

        Ship ship = new Ship();

        ship.setName(createShipDTO.getName());
        ship.setCreated(createShipDTO.getCreated());
        ship.setHealth(createShipDTO.getHealth());
        ship.setPower(createShipDTO.getPower());
        ship.setCategory(category);


        User user = this.userService.findUserById(this.loggedUser.getId());

        ship.setUser(user);

        this.shipRepository.save(ship);

        return true;
    }

    public List<Ship> findAllByUserId(Long id) {

        return this.shipRepository.findAllByUserId(id);
    }

    public List<Ship> findOtherUsersShipsExcludingCurrentUser(Long id) {
        return this.shipRepository.findAllByUserIdIsNot(id);
    }

    public List<Ship> allShipsOrderedByNameHealthPower() {
        return this.shipRepository.findAllOrderByNameHealthPower();
    }

    public void fireFx(FireFxDTO fireFxModel) {

        Optional<Ship> attacker = this.shipRepository.findById(fireFxModel.getAttackerId());
        Optional<Ship> defender = this.shipRepository.findById(fireFxModel.getDefenderId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            return;
        }

        defender.get().setHealth(defender.get().getHealth() - attacker.get().getPower());

        if (defender.get().getHealth() <= 0) {
            this.shipRepository.delete(defender.get());
            return;
        }

        this.shipRepository.save(defender.get());

    }
}
