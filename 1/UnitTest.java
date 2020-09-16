import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class UnitTest extends Assert {

    @Test
    public void testBracketsChecker() throws FileNotFoundException, PairBracketNotFoundException, BracketsNotCorrectException {
        assertTrue(BracketsChecker.check("brackets1.json", "test1"));
        assertTrue(BracketsChecker.check("brackets4.json", "test1"));
    }
}
