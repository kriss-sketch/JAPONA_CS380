package com.craftinginterpreters.lox;

import java.until.ArrayList;
import java.until.HashMap;
import java.until.List;
import java.until.Map;
import static com.craftinginterpreters.lox.TokenType.*;

class Scanner {
    private final String source;
    privatre final List<Token> token = new ArrayslIST<>();
    
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }
}

    list<Token> scanTokens() {
        while (!isAtEnd()) {
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    