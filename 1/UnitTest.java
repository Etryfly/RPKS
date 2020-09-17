import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class UnitTest extends Assert {

    @Test
    public void testBracketsChecker1()  {
        try {
            assertTrue(BracketsChecker.check("brackets1.json", "test1"));
            assertTrue(BracketsChecker.check("brackets4.json", "test1"));
            assertTrue(BracketsChecker.check("brackets1.json", "test2"));
            assertTrue(BracketsChecker.check("brackets1.json", "test3"));
            assertFalse(BracketsChecker.check("brackets2.json", "test2"));
            assertTrue(BracketsChecker.check("brackets3.json", "test2"));
        } catch (FileNotFoundException e) {

        } catch (BracketsNotCorrectException e) {

        } catch (PairBracketNotFoundException e) {

        }


    }


}
