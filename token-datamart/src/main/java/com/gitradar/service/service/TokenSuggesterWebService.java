package com.gitradar.service.service;

import static com.gitradar.service.service.TokenSuggesterWebService.Response.Status.*;

import com.gitradar.service.suggester.DatabaseTokenManager;
import static spark.Spark.port;
import static spark.Spark.get;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.MessageTooLargeException;


public class TokenSuggesterWebService implements TokenSuggesterAPI{
    private final DatabaseTokenManager suggester;
    private final ResponseSerializer serializer;
    public int port = 8080;

    public TokenSuggesterWebService(DatabaseTokenManager suggester) {
        this.suggester = suggester;
        this.serializer = dto -> new Gson().toJson(dto);
    }

    public TokenSuggesterWebService at(int port) {
        this.port = port;
        return this;
    }

    @Override
    public void start() {
        port(port);
        getMethod();
        postMethod();
    }

    private void postMethod() {
        get("/token/post/:context/:word", (req, res) -> {
            try {
                return serializer.serialize(new Response(success, suggester.post(req.params("context"), req.params("word"))));
            }
            catch (MessageTooLargeException e) {
                return serializer.serialize(new Response(too_large_context_provided, new ErrorRecord(e.getMessage(), 413)));
            }
        });
    }

    private void getMethod() {
        get("/token/next/:context", (req, res) -> {
            try {
                return serializer.serialize(new Response(success, suggester.get(req.params("context"))));
            }
            catch (MessageTooLargeException e) {
                return serializer.serialize(new Response(too_large_context_provided, new ErrorRecord(e.getMessage(), 413)));
            }
            catch (Exception e) {
                return serializer.serialize(new Response(not_found, new ErrorRecord("not found", 404)));
            }
        });
    }

    public record Response(Status status, Record data) {

        public enum Status {
            success, too_large_context_provided, not_found
        }
    }

    public record ErrorRecord(String reason, int code) {

    }
}
