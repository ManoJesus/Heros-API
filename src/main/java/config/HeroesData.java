package config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import constants.HeroesConstants;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static constants.HeroesConstants.*;

@Configuration

public class HeroesData {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMODB.toString(), REGION_DYNAMODB.toString() ))
                .build();

        DynamoDB dynamoDb = new DynamoDB(client);

        Table table =  dynamoDb.getTable("HeroesTB");
        //Creating a hero through hashcode
        //Creating a JSON for movies
        Map<String, Integer> wwMovies = new HashMap<String, Integer>();
        wwMovies.put("Wonder Woman", 2017);
        wwMovies.put("Wonder Woman: 1984", 2020);
        Item hero = new Item()
                .withPrimaryKey("id",1)
                .withString("Name","Wonder Woman ")
                .withString("Universe","DC Comics")
                .withMap("Movies/releaseYear",wwMovies);

        PutItemOutcome outcome = table.putItem(hero);
    }
}
