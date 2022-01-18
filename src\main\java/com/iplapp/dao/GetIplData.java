package com.iplapp.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iplapp.domain.Team;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;


public class GetIplData {
    
    public static List<Team> getMongoData() {
        
        List<Team> teamList = new ArrayList<>();
        
      //Creating a MongoDB client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("test");
        //Creating a collection object
        MongoCollection<Document> collection = database.getCollection("IplData");
        //Retrieving the documents
        FindIterable<Document> iterDoc = collection.find();
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
           System.out.println(it.next());
           String json = com.mongodb.util.JSON.serialize(it.next());
           //teamList.add((Team)JSON.parse(json));
           
           System.out.println(JSON.parse(json));
           
           Gson gson = new GsonBuilder().create();
           Team team = gson.fromJson(json, Team.class);
           teamList.add(team);
        }
        return teamList;
    }
}
