package com.builder;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.builder.requestHandler.WordContextDTO;
import com.builder.requestHandler.WordContextDTOProcessor;

import java.util.List;

public class LambdaFunctionHandler implements RequestHandler<List<String>, String> {
    private static final int WindowSize = 25;

    @Override
    public String handleRequest(List<String> tokens, Context context) {
        LambdaLogger logger = context.getLogger();

        for (int i = 0; i < tokens.size() - WindowSize + 1; i++) {
            WordContextDTO wordContext = WordContextDTOProcessor.processWordContext(tokens, WindowSize, i);

            if (wordContext != null) {
                System.out.println("Contexto: " + wordContext.context() + "\n" + "Next: " + wordContext.nextWord());
            }
        }
        return null;
    }
}

