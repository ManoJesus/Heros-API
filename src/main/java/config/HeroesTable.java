package config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static constants.HeroesConstants.ENDPOINT_DYNAMODB;
import static constants.HeroesConstants.REGION_DYNAMODB;

@Configuration
@EnableDynamoDBRepositories
//Deferring from postgresql, dynamoDB don't need to create actually a table. What we do is create the configuration of this entity
// and create the ID, then the table is really created when setting up POST
public class HeroesTable {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMODB.toString(), REGION_DYNAMODB.toString()))
                .build();

        DynamoDB dynamoDb = new DynamoDB(client);

        String tableName = "HeroesTB";

        try{
            Table table = dynamoDb.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L)
                    );
            table.waitForActive();

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
