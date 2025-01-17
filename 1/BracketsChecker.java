import Exceptions.BracketsNotCorrectException;
import Exceptions.PairBracketNotFoundException;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class BracketsChecker {
    private static Brackets getBracketsFromJsonFile(String jsonFileName) throws FileNotFoundException {
        File bracketsFile = new File(jsonFileName);
        StringBuilder resultString = new StringBuilder();

        Scanner reader = new Scanner(bracketsFile);
//        if (!reader.hasNext()) return new Brackets();
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            resultString.append(data);
        }


        Gson gson = new Gson();

        return gson.fromJson(resultString.toString(), Brackets.class);
    }


    private static boolean checkBracketsInFile(Brackets brackets, File file) throws FileNotFoundException, BracketsNotCorrectException, PairBracketNotFoundException {
        Stack<Character> stack = new Stack<>();
        Scanner reader = new Scanner(file);
        reader.useDelimiter("");
        int line = 1;
        StringBuilder equalsBracketsStringBuilder = new StringBuilder();
        while (reader.hasNext()) {
            Character character = reader.next().charAt(0);
            if (brackets.containsLeft(character) && brackets.containsRight(character)) {
                equalsBracketsStringBuilder.append(character);
            } else if (brackets.containsLeft(character)) {
                stack.push(character);
            } else if (brackets.containsRight(character)) {
                if (stack.empty()) {
                    throw new BracketsNotCorrectException(file.getName() + " extra bracket " + character + " line " + line);
                }
                Character bracketFromStack = brackets.getPairByLeft(stack.pop());
                if (bracketFromStack != character) {

                    throw new BracketsNotCorrectException(file.getName() + " " + bracketFromStack + " line " + line);
                }
            } else if (character == '\n') line++;
        }

        if (!stack.empty() && (equalsBracketsStringBuilder.length() % 2 == 0)) {
            char[] equalsBrackets = equalsBracketsStringBuilder.toString().toCharArray();
            for (int i = 0; i < equalsBracketsStringBuilder.length() / 2; i++) {
                if (!(equalsBrackets[i] == equalsBrackets[equalsBracketsStringBuilder.length() - i])) {
                    throw new BracketsNotCorrectException(file.getName() + " " + equalsBrackets[i] + " line " + line);
                }
            }
            return true;
        } else if (!stack.empty()) {
            throw new BracketsNotCorrectException(file.getName() + " close bracket is missing " + stack.pop() + " line " + line);
        }

        return true;
    }

    public static boolean check(String bracketsRule, String fileForCheck) throws FileNotFoundException, BracketsNotCorrectException, PairBracketNotFoundException {
        Brackets brackets = getBracketsFromJsonFile(bracketsRule);

        File file = new File(fileForCheck);

        return checkBracketsInFile(brackets, file);

    }

}