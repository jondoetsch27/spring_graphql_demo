package com.jdd.spring_graphql_demo.graphql;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BasketballTeamService {

    private final BasketballTeamRepository basketballTeamRepository;

    @Autowired
    public BasketballTeamService(BasketballTeamRepository basketballTeamRepository) {
        this.basketballTeamRepository = basketballTeamRepository;
    }

    public List<BasketballTeam> getAllTeams() {
        return basketballTeamRepository.findAll();
    }

    public BasketballTeam getTeamById(String id) {
        return basketballTeamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public BasketballTeam createTeam(String name, String location) {
        BasketballTeam team = new BasketballTeam(null, name, location, new ArrayList<>());
        return basketballTeamRepository.save(team);
    }

    public BasketballTeam updateTeam(String id, String name, String location) {
        BasketballTeam team = getTeamById(id);
        team = new BasketballTeam(id, name, location, team.roster());
        return basketballTeamRepository.save(team);
    }

    public Boolean deleteTeam(String id) {
        if (basketballTeamRepository.existsById(id)) {
            basketballTeamRepository.deleteById(id);
            return true;
        }
        return false;
    }
}