package com.builder.requestHandler;

public record WordContextDTO(String context, String nextWord) {

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

        public WordContextDTO build() {
            return new WordContextDTO(this.context, this.nextWord);
        }
    }
}
