package com.jdd.spring_graphql_demo.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

@Component
public class BasketballQueryResolver {

    private final BasketballTeamService basketballTeamService;
    private final BasketballPlayerService basketballPlayerService;

    public BasketballQueryResolver(BasketballTeamService basketballTeamService, BasketballPlayerService basketballPlayerService) {
        this.basketballTeamService = basketballTeamService;
        this.basketballPlayerService = basketballPlayerService;
    }

    @QueryMapping
    public List<BasketballTeam> getAllTeams() {
        return basketballTeamService.getAllTeams();
    }

    @QueryMapping
    public BasketballTeam getTeamById(String id) {
        return basketballTeamService.getTeamById(id);
    }

    @QueryMapping
    public List<BasketballPlayer> getAllPlayers() {
        return basketballPlayerService.getAllPlayers();
    }

    @QueryMapping
    public BasketballPlayer getPlayerById(String id) {
        return basketballPlayerService.getPlayerById(id);
    }

    @MutationMapping
    public BasketballTeam createTeam(String name, String location) {
        return basketballTeamService.createTeam(name, location);
    }

    @MutationMapping
    public BasketballTeam updateTeam(String id, String name, String location) {
        return basketballTeamService.updateTeam(id, name, location);
    }

    @MutationMapping
    public Boolean deleteTeam(String id) {
        return basketballTeamService.deleteTeam(id);
    }

    @MutationMapping
    public BasketballPlayer createPlayer(String firstName, String lastName, Integer jerseyNumber, List<String> teamRoles) {
        return basketballPlayerService.createPlayer(firstName, lastName, jerseyNumber, teamRoles);
    }

    @MutationMapping
    public BasketballPlayer updatePlayer(String id, String firstName, String lastName, Integer jerseyNumber, List<String> teamRoles) {
        return basketballPlayerService.updatePlayer(id, firstName, lastName, jerseyNumber, teamRoles);
    }

    @MutationMapping
    public Boolean deletePlayer(String id) {
        return basketballPlayerService.deletePlayer(id);
    }

}
