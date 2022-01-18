package com.iplapp.domain;

import java.util.List;

public class Team {
    
    //public String _id;
    public String city;
    public String home;
    public String coach;
    public String name;
    public String label;
    public List<Player> players;
    
    
//    public String getId() {
//        return _id;
//    }
//    public void setId(String id) {
//        this._id = id;
//    }
//    
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getHome() {
        return home;
    }
    public void setHome(String home) {
        this.home = home;
    }
    public String getCoach() {
        return coach;
    }
    public void setCoach(String coach) {
        this.coach = coach;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    @Override
    public String toString() {
        return "Team [city=" + city + ", home=" + home + ", coach=" + coach + ", name=" + name + ", label=" + label
                + ", players=" + players + "]";
    }
    
    
}
