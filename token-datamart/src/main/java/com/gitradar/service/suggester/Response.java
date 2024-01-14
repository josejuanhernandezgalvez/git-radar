package com.gitradar.service.suggester;

public record Response(String context, String nextWord) {

    public static class Builder {
        private String context;
        private String nextWord;

        public Builder context(String context) {
            this.context = context;
            return this;
        }

        public Builder nextWord(String nextWord) {
            this.nextWord = nextWord;
            return this;
        }

        public Response build() {
            return new Response(this.context, this.nextWord);
        }
    }
}
