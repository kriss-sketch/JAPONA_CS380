packages com.craftinginterpreters.lox

class Token {
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;
