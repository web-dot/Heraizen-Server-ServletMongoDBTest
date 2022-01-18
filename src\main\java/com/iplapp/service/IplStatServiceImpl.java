package com.iplapp.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.iplapp.dao.GetIplData;
import com.iplapp.domain.Player;
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
        // TODO Auto-generated method stub
        return null;
    }

}
