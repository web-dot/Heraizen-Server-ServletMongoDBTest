package com.iplapp.service;

import java.util.List;

import org.bson.Document;

import com.iplapp.domain.Team;

public interface IplStatService {
    
    
    List<Team> getPlayersByTeam();
    List<String> teamInfoByLabel();
    boolean searchTeam();
    void testLabel();
    void getPlayers();
    List<String> getPlayersBasedOnCity(String city);
}
