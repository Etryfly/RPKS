
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

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
