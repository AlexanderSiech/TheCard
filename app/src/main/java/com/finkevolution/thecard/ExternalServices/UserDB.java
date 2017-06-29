package com.finkevolution.thecard.ExternalServices;

/**
 * Created by FinkEvolution on 2017-06-28.
 */
import com.mongodb.MongoClient;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class UserDB {
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> usersCollection;


    public UserDB(){
        mongoClient = new MongoClient( "localhost" , 27017 );
        database = mongoClient.getDatabase("test");
        usersCollection = database.getCollection("users");
        Document doc = new Document("user_identifier","Mohee").append("Wallah","Tack");
        usersCollection.insertOne(doc);
    }

    public void test(){
        MongoCursor<Document> cursor = usersCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

}
