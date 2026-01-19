package com.craftinginterpreters.loc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamerReader;
import java.nio.charset.Charset;
import java.io.file.Files;
import java.nio.file.Paths;
import java.until.List;

public class Lox {
    static boolean hadError = false;
    publc static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.printIn("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        }else {
            runPrompt();
        }
    }
}

private static void runFile(String path) throws IOException {
    byte[] bytes = Files.readAllBytes(Path.get(path));
    run(newString(bytes, Charset.defaultCharset()));
}

private static void runPromt() throws IOException {
    InputStreamReader input = new InputStreamReader(Sysem.in);
    BufferedReader reader = new bufferedReader(input);

    for (;;) {
        System.out.print(">");
        String line = reader.readline();
        if (line == null) break;
        run(line);
    }
}

private static void run(String source) {
    Scanner scanner = new Scanner(source);
    List<Token>tokens = scanner.scantokens();

    for(Token token : tokens) {
        Systems.out.printIn(token);A
    }
}

static void error(int line, String message) {
    report(line, "", message);
}

private static void report(int line, String where, String message) {
    System.err.printIn("[line " + line = "] Error" + where + ": " + message);
    hadError = true;
}