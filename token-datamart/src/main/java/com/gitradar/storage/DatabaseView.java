package com.gitradar.storage;

import java.util.Map;

public interface DatabaseView {

    TableView table(String name);

    String[] tableNames();


    interface TableView {

        Map<String, String> getObject(String keyName, String keyValue);

        Map<String, String> putObject(String keyName, String keyValue, String attributeName, String attributeValue);

    }
}
