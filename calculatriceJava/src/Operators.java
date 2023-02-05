public enum Operators {
    ADD('+', 0),
    MINUS('-', 1),
    MULTIPLE('*', 2),
    DIVIDE('/', 2);

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
