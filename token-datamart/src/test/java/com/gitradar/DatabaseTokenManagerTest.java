package com.gitradar;

import com.gitradar.service.suggester.DatabaseTokenManager;
import com.gitradar.service.suggester.WordContextDTO;
import com.gitradar.storage.DatabaseView;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.gitradar.storage.DatabaseView.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseTokenManagerTest {
    private DatabaseTokenManager databaseTokenManager;
    private final String context = "context";
    private final String nextWord = "nextWord";

    @Before
    public void setUp() {
        DatabaseView mockDatabaseView = mock(DatabaseView.class);
        TableView mockTableView = mock(TableView.class);
        databaseTokenManager = new DatabaseTokenManager(mockDatabaseView);

        when(mockDatabaseView.table(anyString()))
                .thenReturn(mockTableView);
        when(mockTableView.getObject(anyString(), anyString()))
                .thenReturn(Map.of("context", context, "word", nextWord));
        when(mockDatabaseView.tableNames())
                .thenReturn(new String[]{"ngram01"});
    }

    @Test
    public void testGet() {
        WordContextDTO result = databaseTokenManager.get(context);
        assertEquals(context, result.context());
        assertEquals(nextWord, result.nextWord());
    }
}
