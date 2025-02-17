package com.jdd.spring_graphql_demo.graphql;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BasketballPlayers")
public record BasketballPlayer (
    @Id String id, 
    String firstName, 
    String lastName, 
    Integer jerseyNumber, 
    String currentTeam,
    List<String> teamRoles
) {
}
