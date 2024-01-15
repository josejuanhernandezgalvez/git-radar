package com.gitradar.service.manager;

import com.gitradar.storage.DatabaseView;
import org.eclipse.jetty.websocket.api.MessageTooLargeException;

import java.util.Arrays;
import java.util.Map;

import static java.lang.String.format;

public class DatabaseTokenManager implements TokenManager {
    private final String contextIsTooLarge = "exceeded the size limit for context";
    public static final int numberOfDigitsForEachTable = 2;
    public static final String separator = ",";
    public String attributeName = "word";
    public String keyName = "context";
    private final DatabaseView databaseView;

    public DatabaseTokenManager(DatabaseView databaseView) {
        this.databaseView = databaseView;
    }

    @Override
    public WordContextDTO get(String context) {
        if (context.split(separator).length > maxContext()) throw new MessageTooLargeException(contextIsTooLarge);
        Map<String, String> response = tableFor(context).getObject(keyName, context);
        return new WordContextDTO.Builder()
                           .context(response.get(keyName))
                           .nextWord(response.get(attributeName))
                           .build();
    }

    @Override
    public WordContextDTO post(String context, String nextWord) {
        if (context.split(separator).length > maxContext()) throw new MessageTooLargeException(contextIsTooLarge);
        tableFor(context).putObject(keyName, context, attributeName, nextWord);
        return new WordContextDTO.Builder()
                           .context(context)
                           .nextWord(nextWord)
                           .build();
    }

    public int maxContext() {
        return databaseView.tableNames().length;
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
