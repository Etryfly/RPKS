import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class MoneyChangerTest extends Assert {
    ArrayList<Integer> coins = new ArrayList<>();
    ArrayList<Integer> result = new ArrayList<>();
    ArrayList<Integer> change = new ArrayList<>();



    @Test
    public void test1MoneyChanger() {
        coins.add(4);
        coins.add(2);
        int sum = 10;
        result.add(4);
        result.add(4);
        result.add(2);


        Collections.sort(result);


        assertTrue(MoneyChangerMain.getChange(sum,change,  coins, 0));
        Collections.sort(change);

        assertArrayEquals(  change.toArray(), result.toArray() );
    }

//
//    @Test
//    public void test2MoneyChanger()  {
//        coins.add(2);
//        coins.add(4);
//        int sum = 10;
//
//        result.add(4);
//        result.add(4);
//        result.add(2);
//        Collections.sort(result);
//        assertTrue(MoneyChangerMain.getChange(sum,change,  coins, 0));
//        Collections.sort(change);
//
//        assertArrayEquals(  change.toArray(), result.toArray() ); //2 2 2 2 2
//    }


    @Test
    public void test3MoneyChanger() {
        coins.add(4);
        coins.add(2);
        int sum = 11;

        assertFalse(MoneyChangerMain.getChange(sum,change,  coins, 0));

    }


    @Test
    public void test4MoneyChanger()  {
        coins.add(4);
        coins.add(2);
        int sum = 1;

        assertFalse(MoneyChangerMain.getChange(sum,change,  coins, 0));
    }


    @Test
    public void test5MoneyChanger() {
        coins.add(4);
        coins.add(2);
        int sum = 2;
        result.add(2);

        Collections.sort(result);


        assertTrue(MoneyChangerMain.getChange(sum,change,  coins, 0));
        Collections.sort(change);

        assertArrayEquals(  change.toArray(), result.toArray() );
    }
}
