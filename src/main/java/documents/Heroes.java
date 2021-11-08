package documents;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@DynamoDBTable(tableName ="HeroesTB")
public class Heroes {
    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;
    @DynamoDBAttribute(attributeName = "name")
    private String name;
    @DynamoDBAttribute(attributeName = "universe")
    private String universe;
    @DynamoDBAttribute(attributeName = "Movies/releaseYear")
    private Map<String, Integer> movie_year;

    public Heroes(String id, String name, String universe, Map<String, Integer> movie_year) {
        this.id = id;
        this.name = name;
        this.universe = universe;
        this.movie_year = movie_year;
    }
}
