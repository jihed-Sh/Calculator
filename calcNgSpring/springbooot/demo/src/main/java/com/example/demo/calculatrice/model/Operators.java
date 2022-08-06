package com.example.demo.calculatrice.model;

public enum Operators {
    ADD('+', 0),
    MINUS('-', 0),
    MULTIPLE('*', 1),
    DIVIDE('/', 1);

    private final int precedence;
    private final char character;

    private Operators(char character, int precedence){
        this.character=character;
        this.precedence=precedence;
    }
    public char getCharacter() {
        return character;
    }

    public int getPrecedence() {
        return precedence;
    }

}
