import com.builder.TokenWebService;
import com.builder.requestHandler.WordContextDTO;
import com.builder.requestHandler.WordContextDTOProcessor;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int MaxWindowSize = 25;
        TokenWebService tokenWebService = new TokenWebService();
        List<String> tokenizedCode = List.of("hola", "que", "haces");

        for (int windowSize = 1; windowSize <MaxWindowSize; windowSize++) {
            for (int j = 0; j < tokenizedCode.size() - windowSize + 1; j++) {
                WordContextDTO wordContext = WordContextDTOProcessor.processWordContext(tokenizedCode, windowSize, j);
                if (wordContext == null) continue;
                tokenWebService.post(wordContext.context().replace(" ", ","), wordContext.nextWord());
            }
        }
    }
}
