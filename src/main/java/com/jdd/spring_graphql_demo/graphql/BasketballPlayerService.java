package com.jdd.spring_graphql_demo.graphql;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class BasketballPlayerService {

    private final BasketballPlayerRepository basketballPlayerRepository;

    @Autowired
    public BasketballPlayerService(BasketballPlayerRepository basketballPlayerRepository) {
        this.basketballPlayerRepository = basketballPlayerRepository;
    }

    public List<BasketballPlayer> getAllPlayers() {
        return basketballPlayerRepository.findAll();
    }

    public BasketballPlayer getPlayerById(String id) {
        return basketballPlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public BasketballPlayer createPlayer(String firstName, String lastName, Integer jerseyNumber, List<String> teamRoles) {
        BasketballPlayer player = new BasketballPlayer(null, firstName, lastName, jerseyNumber, teamRoles);
        return basketballPlayerRepository.save(player);
    }

    public BasketballPlayer updatePlayer(String id, String firstName, String lastName, Integer jerseyNumber, List<String> teamRoles) {
        BasketballPlayer player = getPlayerById(id);
        player = new BasketballPlayer(id, firstName, lastName, jerseyNumber, teamRoles);
        return basketballPlayerRepository.save(player);
    }

    public Boolean deletePlayer(String id) {
        if (basketballPlayerRepository.existsById(id)) {
            basketballPlayerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
