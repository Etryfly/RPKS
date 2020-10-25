import Exceptions.BracketsNotCorrectException;
import Exceptions.PairBracketNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class UnitTest extends Assert {

    @Test
    public void testBracketsChecker1() {
        try {
            assertTrue(BracketsChecker.check("brackets1.json", "test1"));
            assertTrue(BracketsChecker.check("brackets4.json", "test1"));
            assertTrue(BracketsChecker.check("brackets1.json", "test2"));
            assertTrue(BracketsChecker.check("brackets1.json", "test3"));
            assertFalse(BracketsChecker.check("brackets2.json", "test2"));
            assertTrue(BracketsChecker.check("brackets3.json", "test2"));
        } catch (PairBracketNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (BracketsNotCorrectException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }


}
