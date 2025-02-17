package com.jdd.spring_graphql_demo.graphql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "BasketballTeams")
public record BasketballTeam(
    @Id String id, 
    String name, 
    String location, 
    List<String> roster
) {
}
