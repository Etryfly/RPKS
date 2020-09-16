import java.util.List;

public class Brackets {
    public List<Bracket> brackets;




    public boolean containsLeft(Character leftBracket) {
        for (Bracket bracket:
             brackets) {
            if (bracket.getLeft() == leftBracket) return true;
        }
        return false;
    }


    public boolean containsRight(Character rightBracket) {
        for (Bracket bracket:
                brackets) {
            if (bracket.getRight() == rightBracket) return true;
        }
        return false;
    }

    public Character getPairByLeft(Character left) throws PairBracketNotFoundException {
        for (int i = 0; i < brackets.size(); i++) {
            if (brackets.get(i).getLeft() == left) return brackets.get(i).getRight();
        }
        throw new PairBracketNotFoundException(left);
    }


    public Character getPairByRight(Character right) throws PairBracketNotFoundException {
        for (int i = 0; i < brackets.size(); i++) {
            if (brackets.get(i).getRight() == right) return brackets.get(i).getLeft();
        }
        throw new PairBracketNotFoundException(right);
    }
}
