package com.suggester.token;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.suggester.api.JsonProcessor;

public class NextTokenJsonProcessor implements JsonProcessor {

    private static final String STATUS_FIELD_NAME = "status";

    private static final String DATA_FIELD_NAME = "data";
    private static final String TARGET_FIELD_NAME = "nextWord";

    public String process(String json){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        if (!jsonObject.get(STATUS_FIELD_NAME).toString().equals("\"success\"")) return "Word not found";
        JsonObject dataObject = jsonObject.getAsJsonObject(DATA_FIELD_NAME);
        return dataObject.get(TARGET_FIELD_NAME).getAsString();
    }
}
