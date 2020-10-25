package Exceptions;

public class PairBracketNotFoundException extends Exception {
    public static Character PairBracket;

    public PairBracketNotFoundException(Character pairBracket) {
        PairBracket = pairBracket;
    }
}
