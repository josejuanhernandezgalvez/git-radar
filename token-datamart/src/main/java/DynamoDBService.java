import com.amazonaws.AmazonServiceException;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DynamoDBService {
    public final String region;
    public final String ip;
    public final int port;
    public final AmazonDynamoDB client;
    public final String[] tables;

    protected DynamoDBService(String ip, int port, String region) {
        this.ip = ip;
        this.port = port;
        this.region = region;
        this.client = AmazonDynamoDBClientBuilder.standard()
                            .withEndpointConfiguration(endpointConfiguration())
                            .build();
        this.tables = this.client.listTables()
                                    .getTableNames()
                                    .toArray(String[]::new);
    }

    private EndpointConfiguration endpointConfiguration() {
        return new EndpointConfiguration(String.format("%s:%s", this.ip, this.port), this.region);
    }

    public TableView table(String name) {
        if (Arrays.asList(this.tables).contains(name)) return new TableView(name, client);
        throw new RuntimeException(String.format("No table with the name specified: %s", name));
    }

    public static class TableView {
        public final String name;
        public final AmazonDynamoDB client;
        private final Table table;

        private TableView(String name, AmazonDynamoDB client) {
            this.name = name;
            this.client = client;
            this.table = new DynamoDB(client).getTable(name);
        }

        public Map<String, String> getObject(String keyValue) {
            try {
                return map(table.getItem("context", keyValue));
            } catch (AmazonServiceException e) {
                return new HashMap<>();
            }
        }

        public Map<String, String> putObject(String keyName, String keyValue, String attributeName, String attributeValue) {
            Item item = new Item()
                    .withPrimaryKey(keyName, keyValue)
                    .withString(attributeName, attributeValue);
            table.putItem(item);
            return map(item);
        }

        private Map<String, String> map(Item item) {
            Map<String, String> result = new HashMap<>();
            for (Map.Entry<String, Object> entry : item.asMap().entrySet())
                result.put(entry.getKey(), entry.getValue().toString());
            return result;
        }
    }


    public static class Builder {
        private String region;
        private String ip;
        private int port = 4566;


        public Builder in(String region) {
            this.region = region;
            return this;
        }

        public Builder ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public DynamoDBService build() {
            return new DynamoDBService(this.ip, this.port, this.region);
        }
    }
}
