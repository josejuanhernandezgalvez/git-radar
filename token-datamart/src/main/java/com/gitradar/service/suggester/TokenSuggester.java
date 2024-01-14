package com.gitradar.service.suggester;

import com.gitradar.storage.DatabaseView;

import java.util.Arrays;
import java.util.Map;

import static java.lang.String.format;

public class TokenSuggester {
    public static final int numberOfDigitsForEachTable = 2;
    public static final String separator = ",";
    private final DatabaseView databaseView;
    public String keyName = "context";
    public String attributeName = "word";

    public TokenSuggester(DatabaseView databaseView) {
        this.databaseView = databaseView;
    }

    public Response get(String context) {
        Map<String, String> response = tableFor(context).getObject(keyName, context);
        return new Response.Builder()
                           .context(response.get(keyName))
                           .nextWord(response.get(attributeName))
                           .build();
    }

    public Response put(String context, String nextWord) {
        tableFor(context).putObject(keyName, context, attributeName, nextWord);
        return new Response.Builder()
                           .context(context)
                           .nextWord(nextWord)
                           .build();
    }

    private DatabaseView.TableView tableFor(String context) {
        return databaseView.table(Arrays.stream(databaseView.tableNames())
                                 .filter(tableName -> tableName.endsWith(identifier(context)))
                                 .findFirst().get());
    }

    private static String identifier(String context) {
        return format(tableIdentifier(), context.split(separator).length);
    }

    private static String tableIdentifier() {
        return "%0" + numberOfDigitsForEachTable + "d";
    }
}
