package com.iplapp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.iplapp.dao.GetIplData;
import com.iplapp.domain.Team;

public class IplStatServiceImpl implements IplStatService {
    
    private List<Team> teamList;
    private static transient IplStatServiceImpl obj = null;
    public String city="";
    
    
    private IplStatServiceImpl() throws JsonParseException, IOException {
       teamList = GetIplData.getMongoData();
    }
    
    
    public static IplStatService getInstance() throws JsonParseException, IOException {
        if(obj==null) {
            synchronized (IplStatServiceImpl.class) {
                obj=new IplStatServiceImpl();
                System.out.println("object created");
            }
        }
        return obj;
    }
    
    @Override
    public List<Team> getPlayersByTeam() {
        return teamList; 
    }



    @Override
    public List<String> teamInfoByLabel() {
        Map<String, List<String>> map = new HashMap<>();
        
        Predicate<Team> p = new Predicate<Team>() {
            @Override
            public boolean test(Team t) {
                for(Team team : teamList) {
                    if(team.label.equals("MI")) {
                        return true;
                    }
                }
                return false;
            }
        };
        
        Stream<Team> t = teamList.stream().filter(p);
        System.out.println("->");
        t.forEach(System.out::println);
        
        return null;
    }
    
    
    @Override
    public boolean searchTeam() {
        boolean result = GetIplData.srchTeamInDb();
        return result;
    }
    
    
    @Override
    public void testLabel() {
     GetIplData.testLabel();
        
    }
    
    @Override
    public void getPlayers() {
        GetIplData.getPlayersBasedOnLabel();
    }
    
    @Override
    public List<String> getPlayersBasedOnCity(){
        return GetIplData.getPlayersBasedOnCity();
    }
    
    
    
    
    

}
