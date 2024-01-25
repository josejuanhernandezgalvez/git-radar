package com.tokenizer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.tokenizer.utils.SourceCodeDeserializer;
import com.tokenizer.utils.TokenizedCodeSerializer;
import com.tokenizer.context.SourceCode;
import com.tokenizer.context.TokenizedCode;
import com.tokenizer.context.tokenizer.Tokenizer;
import com.tokenizer.context.tokenizer.TokenizerInitializer;

import java.util.List;

public class LambdaFunctionHandler implements RequestHandler<String, String> {
    private static final SourceCodeDeserializer parser = new SourceCodeDeserializer();
    private static final TokenizedCodeSerializer deserializer = new TokenizedCodeSerializer();

    @Override
    public String handleRequest(String s, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("String found: " + s);

        SourceCode sourceCode = parser.deserialize(s);

        Tokenizer tokenizer = TokenizerInitializer.initializeTokenizer(sourceCode.getName());

        List<String> tokenized = tokenizer.tokenize(sourceCode.getCode());

        TokenizedCode tokenizedCode = new TokenizedCode(tokenized, System.currentTimeMillis());

        return deserializer.serialize(tokenizedCode);
    }
}

