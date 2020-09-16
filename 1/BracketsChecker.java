import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class BracketsChecker {
    private static Brackets getBracketsFromJsonFile(java.lang.String jsonFileName) throws FileNotFoundException {
        File bracketsFile = new File(jsonFileName);
        StringBuilder resultString = new StringBuilder();

        Scanner reader = new Scanner(bracketsFile);
        while (reader.hasNextLine()) {
            java.lang.String data = reader.nextLine();
            resultString.append(data);
        }


        Gson gson = new Gson();

        return gson.fromJson(resultString.toString(), Brackets.class);
    }


    private static boolean checkBracketsInFile(Brackets brackets, File file) throws FileNotFoundException, BracketsNotCorrectException, PairBracketNotFoundException {
        Stack<Character> stack = new Stack<Character>();
        Scanner reader = new Scanner(file);
        reader.useDelimiter("");
        while (reader.hasNext()) {
            Character character = reader.next().charAt(0);
            if (brackets.containsLeft(character)) {
                stack.push(character);
            } else if (brackets.containsRight(character)) {
                if (stack.empty()) {
                    throw new BracketsNotCorrectException(file.getName() + " extra bracket " + character);
                }
                Character bracketFromStack = brackets.getPairByLeft(stack.pop());
                if (bracketFromStack != character) {
                    //скобки не совпали, кидаем исключение о том, что все плохо
                    throw new BracketsNotCorrectException(file.getName() + " " + bracketFromStack);
                }
            }
        }
        if (!stack.empty()) {
            throw new BracketsNotCorrectException(file.getName() + " Bracket is missing " + stack.pop());
        }
        return true;
    }

    public static boolean check(String bracketsRule, String fileForCheck) throws FileNotFoundException, BracketsNotCorrectException, PairBracketNotFoundException {
        Brackets brackets = getBracketsFromJsonFile(bracketsRule);


        File file = new File(fileForCheck);

        return checkBracketsInFile(brackets, file);


    }

}