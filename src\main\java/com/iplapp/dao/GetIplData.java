package com.iplapp.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iplapp.domain.Team;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;




public class GetIplData {
    
    
    
    static String db_name = "test";
    static String db_collection_name = "IplData";
    
    
    public static MongoClient getConnection() {
      //connect to mongoDb instance
        final String uriString = "mongodb://localhost:27017/test";
        MongoClientURI uri = new MongoClientURI(uriString);
        MongoClient mongoClient = new MongoClient(uri);
        return mongoClient;
    }
    
    //method to search a team in mongodb
    public static boolean srchTeamInDb() {
        boolean team_found = false;
        
        //get mongodb connection
        MongoDatabase db = getConnection().getDatabase(db_name);
        
        //get the mongodb collection
        MongoCollection col = db.getCollection(db_collection_name);
        
        String team = "Mumbai Indians";
        List obj = new ArrayList();
        obj.add(new BasicDBObject("name", team));
        
        //form a where query
        
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("$and", obj);
        System.out.println("sql query is?=" + whereQuery.toString());
        
        FindIterable<Document> cursor = col.find(whereQuery);
        
        for(Document doc : cursor) {
            System.out.println("Found?=" + doc);
            team_found = true;
        }
        return team_found;   
    }
    
    
    public static void testLabel() {
        String db_name = "test";
        String db_collection_name = "IplData";
        DBCollection col = getConnection().getDB(db_name).getCollection(db_collection_name);
        
        //Document teamStat = col.find(eq("label", "MI")).first();
        //System.out.println("team by label= " + teamStat.toString());  
    }
    
    public static List<String> getPlayersBasedOnCity() {
        MongoCollection<Document> collection =  getConnection().getDatabase("test").getCollection("IplData");
        
        List<String> players = new ArrayList<String>();
        String cityState = "Mumbai, Maharashtra";
        
        FindIterable<Document> findIterable = collection.find(eq("city", cityState)).projection(fields(include("players")));
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
                players.add(document.toJson());
            }
        };
        findIterable.forEach(printBlock);
        return players;
    }
    
    
    
    
    
    public static void getPlayersBasedOnLabel() {
        String db_name = "test";
        String db_collection_name = "IplData";
        DBCollection col = getConnection().getDB(db_name).getCollection(db_collection_name);
        
        AggregationOutput aggregate = col.aggregate((Arrays.asList(new BasicDBObject("$unwind", "$players"))));
        
        System.out.println(aggregate.results());
        
    }
    
    
    
    
    
    
    
    
    
    
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
           //System.out.println(it.next());
           String json = com.mongodb.util.JSON.serialize(it.next());
           //teamList.add((Team)JSON.parse(json));
           
           //System.out.println(JSON.parse(json));
           
           Gson gson = new GsonBuilder().create();
           Team team = gson.fromJson(json, Team.class);
           teamList.add(team);
           mongo.close();
        }
        return teamList;
    }
    

}
