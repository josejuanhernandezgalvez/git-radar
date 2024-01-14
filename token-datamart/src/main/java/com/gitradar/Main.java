package com.gitradar;

import com.gitradar.DatabaseService.TableView;
import com.gitradar.services.DynamoDatabaseService;

public class Main {
    public static void main(String[] args) {
        TableView table = new DynamoDatabaseService.Builder()
                .in("eu-central-2")
                .port(4566)
                .ip("http://localhost")
                .build()
                .table("ngram01");

        System.out.println(table.putObject("context","tal",
                "word","estas"));

        System.out.println(table.getObject("context", "hola"));
    }
}
