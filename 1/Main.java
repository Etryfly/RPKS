
import Exceptions.BracketsNotCorrectException;
import Exceptions.PairBracketNotFoundException;

import java.io.FileNotFoundException;

public class Main {





    public static void main(java.lang.String[] args) {
        if (args.length <2) {
            System.out.println("No arguments specified");
            return;
        }



        try {
            if(BracketsChecker.check(args[0], args[1])) {
                System.out.println("Brackets is correct");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Checked file not found, file name = " + e.getMessage());
        } catch (BracketsNotCorrectException e) {
            System.out.println("Brackets not correct:" + e.getMessage() );
        } catch (PairBracketNotFoundException e) {
            System.out.println("Problem with brackets file" + e.getMessage());
        }




    }



}
