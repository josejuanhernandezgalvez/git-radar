package com.gitradar;

import java.util.Map;

public interface DatabaseService {

    TableView table(String name);


    interface TableView {

        Map<String, String> getObject(String keyName, String keyValue);

        Map<String, String> putObject(String keyName, String keyValue, String attributeName, String attributeValue);

    }
}
