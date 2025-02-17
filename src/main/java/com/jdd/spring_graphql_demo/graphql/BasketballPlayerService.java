package com.jdd.spring_graphql_demo.graphql;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketballPlayerService {

    private final BasketballPlayerRepository basketballPlayerRepository;
    private final BasketballTeamRepository basketballTeamRepository;
    
    public BasketballPlayerService(BasketballPlayerRepository basketballPlayerRepository, BasketballTeamRepository basketballTeamRepository) {
        this.basketballPlayerRepository = basketballPlayerRepository;
        this.basketballTeamRepository = basketballTeamRepository;
    }

    public List<BasketballPlayer> getAllPlayers() {
        return basketballPlayerRepository.findAll();
    }

    public BasketballPlayer getPlayerById(String id) {
        return basketballPlayerRepository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));
    }

    public BasketballPlayer createPlayer(String firstName, String lastName, Integer jerseyNumber, String currentTeam, List<String> teamRoles) {
        BasketballPlayer player = new BasketballPlayer(null, firstName, lastName, jerseyNumber, currentTeam, teamRoles);
        player = basketballPlayerRepository.save(player);
        BasketballTeam team = basketballTeamRepository.findById(currentTeam).orElseThrow(() -> new RuntimeException("Team not found"));
        List<String> updatedRoster = new ArrayList<>(team.roster());
        updatedRoster.add(player.id());
        team = new BasketballTeam(team.id(), team.name(), team.location(), updatedRoster);
        basketballTeamRepository.save(team);
        return player;
    }

    public BasketballPlayer updatePlayer(String id, String firstName, String lastName, Integer jerseyNumber, String currentTeam, List<String> teamRoles) {
        BasketballPlayer player = getPlayerById(id);
        player = new BasketballPlayer(id, firstName, lastName, jerseyNumber, currentTeam, teamRoles);
        player = basketballPlayerRepository.save(player);
        
        if (!player.currentTeam().equals(currentTeam)) {
            BasketballTeam oldTeam = basketballTeamRepository.findById(player.currentTeam()).orElseThrow(() -> new RuntimeException("Old team not found"));
            List<String> oldRoster = new ArrayList<>(oldTeam.roster());
            oldRoster.remove(player.id());
            oldTeam = new BasketballTeam(oldTeam.id(), oldTeam.name(), oldTeam.location(), oldRoster);
            basketballTeamRepository.save(oldTeam);
            
            BasketballTeam newTeam = basketballTeamRepository.findById(currentTeam).orElseThrow(() -> new RuntimeException("New team not found"));
            List<String> newRoster = new ArrayList<>(newTeam.roster());
            newRoster.add(player.id());
            newTeam = new BasketballTeam(newTeam.id(), newTeam.name(), newTeam.location(), newRoster);
            basketballTeamRepository.save(newTeam);
        }
        
        return player;
    }

    public Boolean deletePlayer(String id) {
        BasketballPlayer player = basketballPlayerRepository.findById(id).orElse(null);
        if (player != null) {
            BasketballTeam team = basketballTeamRepository.findById(player.currentTeam()).orElseThrow(() -> new RuntimeException("Team not found"));
            List<String> updatedRoster = new ArrayList<>(team.roster());
            updatedRoster.remove(player.id());
            team = new BasketballTeam(team.id(), team.name(), team.location(), updatedRoster);
            basketballTeamRepository.save(team);
            basketballPlayerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
