package config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {
    /*
    * placeholders for aws configuration values
    * */
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    @Value("${aws_access_key_id}")
    private String awsAccessKeyId;
    @Value("${aws_secret_access_key}")
    private String awsSecretAccessKeyId;

    @Bean
    public AmazonDynamoDB getAmazonDynamoDB() {
            AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(getAWSCred());
            if(!StringUtils.isEmpty(amazonDynamoDBEndpoint)){
                amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
            }
            return amazonDynamoDB;
    }
    @Bean
    public AWSCredentials getAWSCred(){
        return new BasicAWSCredentials(
                awsAccessKeyId,
                awsSecretAccessKeyId
        );
    }
}
