package metrics.utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import metrics.SourceCode;

public class JsonCodeParser implements Parser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SourceCode parseJson(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, SourceCode.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
