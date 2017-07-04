package com.finkevolution.thecard.ExternalServices;

/**
 * Created by FinkEvolution on 2017-06-28.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.finkevolution.thecard.Activites.MainActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.mongodb.stitch.android.PipelineStage;
import com.mongodb.stitch.android.StitchClient;
import com.mongodb.stitch.android.auth.Auth;
import com.mongodb.stitch.android.auth.AvailableAuthProviders;
import com.mongodb.stitch.android.auth.anonymous.AnonymousAuthProvider;
import com.mongodb.stitch.android.services.mongodb.MongoClient;

// Used for inserting or retrieving documents from MongoDB
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;


// used for authentication and anonymous auth


public class UserDB {
    MongoClient mongoClient;
    MongoClient.Database database;
    MongoCollection<Document> usersCollection;
    private static final String APP_ID = "klippkortet-uiokb"; //The Stitch Application ID
    private static final String MONGODB_SERVICE_NAME = "mongodb-atlas";

    private StitchClient _client;
    private MongoClient _mongoClient;


    public UserDB() {
        connectToMongoAtlas();
        /**   usersCollection = database.getCollection("users");
         Document doc = new Document("user_identifier","Mohee").append("Wallah","Tack");
         usersCollection.insertOne(doc);
         **/
    }

    public void connectToMongoAtlas() {
        _client = new StitchClient(MainActivity.getContext(), APP_ID);
        _mongoClient = new MongoClient(_client, MONGODB_SERVICE_NAME);
        System.out.println("Connected");
        authenticate();
        //   testWrite();
        testPipe();
    }

    public void authenticate() {
        _client.getAuthProviders().addOnCompleteListener(new OnCompleteListener<AvailableAuthProviders>() {
            @Override
            public void onComplete(@NonNull final Task<AvailableAuthProviders> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().hasAnonymous()) {
                     /*
                     * Service enabled anonymous authentication
                     * */
                        Log.d("Service Enabled Anon", " True");

                        //login anonymously
                        _client.logInWithProvider(new AnonymousAuthProvider()).continueWith(new Continuation<Auth, Object>() {
                            @Override
                            public Object then(@NonNull final Task<Auth> task) throws Exception {
                                if (task.isSuccessful()) {
                                    Log.d("Logged in: ", "Anonymously");   //we are logged in anonymously
                                } else {
                                    Log.d("Logged in: ", "FAILED");
                                    //anonymous login failed
                                }
                                return null;
                            }
                        });
                    } else {
                        Log.d("Service: ", "Anon Atuhenication Not Allowed");
                        //the service doesn't allow anonymous authentication
                    }
                }
            }
        });

    }


    public void testWrite() {
        Document query = new Document();
        query.put("name", "Mohee");

        Document update = new Document("$push", new Document("comments", query));
        _mongoClient.getDatabase("Klippkortet").getCollection("Users").insertOne(query).continueWith(new Continuation<Void, Object>() {
            @Override
            public Object then(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Log.d("Success", "Yaay");
                else Log.e("Error", "Error");
                return null;
            }
        });
    }

    public void testPipe() {
        List<Document> items = new ArrayList<>();
        items.add(new Document("result", "%%vars.geo_matches"));
        Document argsMap = new Document().append("userId", "Wallah").append("from", "Ukraine");
        Document pipeLine = new Document().append("name", "insertUser").append("args", argsMap);
        PipelineStage literalStage = new PipelineStage("literal", new Document("items", items),
                new Document("geo_matches", new Document("$pipeline", pipeLine)));

        _client.executePipeline(literalStage);
    }
}


