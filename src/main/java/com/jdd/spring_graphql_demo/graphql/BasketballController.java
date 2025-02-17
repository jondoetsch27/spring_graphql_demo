package com.jdd.spring_graphql_demo.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BasketballController {

    private final BasketballTeamService basketballTeamService;
    private final BasketballPlayerService basketballPlayerService;

    public BasketballController(BasketballTeamService basketballTeamService, BasketballPlayerService basketballPlayerService) {
        this.basketballTeamService = basketballTeamService;
        this.basketballPlayerService = basketballPlayerService;
    }

    @QueryMapping
    public List<BasketballTeam> getAllTeams() {
        return basketballTeamService.getAllTeams();
    }

    @QueryMapping
    public BasketballTeam getTeamById(@Argument String id) {
        return basketballTeamService.getTeamById(id);
    }

    @QueryMapping
    public List<BasketballPlayer> getAllPlayers() {
        return basketballPlayerService.getAllPlayers();
    }

    @QueryMapping
    public BasketballPlayer getPlayerById(@Argument String id) {
        return basketballPlayerService.getPlayerById(id);
    }

    @MutationMapping
    public BasketballTeam createTeam(@Argument String name, @Argument String location, @Argument List<String> roster) {
        return basketballTeamService.createTeam(name, location, roster);
    }

    @MutationMapping
    public BasketballTeam updateTeam(@Argument String id, @Argument String name, @Argument String location, @Argument List<String> roster) {
        return basketballTeamService.updateTeam(id, name, location, roster);
    }

    @MutationMapping
    public Boolean deleteTeam(@Argument String id) {
        return basketballTeamService.deleteTeam(id);
    }

    @MutationMapping
    public BasketballPlayer createPlayer(@Argument String firstName, @Argument String lastName, @Argument Integer jerseyNumber, @Argument String currentTeam, @Argument List<String> teamRoles) {
        return basketballPlayerService.createPlayer(firstName, lastName, jerseyNumber, currentTeam, teamRoles);
    }

    @MutationMapping
    public BasketballPlayer updatePlayer(@Argument String id, @Argument String firstName, @Argument String lastName, @Argument Integer jerseyNumber, @Argument String currentTeam, @Argument List<String> teamRoles) {
        return basketballPlayerService.updatePlayer(id, firstName, lastName, jerseyNumber, currentTeam, teamRoles);
    }

    @MutationMapping
    public Boolean deletePlayer(@Argument String id) {
        return basketballPlayerService.deletePlayer(id);
    }

}
