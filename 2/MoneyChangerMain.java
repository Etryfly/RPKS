import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class MoneyChangerMain {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Sum: ");
        int sum = reader.nextInt();
        int n = 0;
        System.out.println("Enter count of coins");
        n = reader.nextInt();
        System.out.println("Coins: ");
        ArrayList<Integer> coins = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            coins.add(reader.nextInt());
        }

        ArrayList<Integer> change = new ArrayList<>();
        boolean isSuccess = getChange(sum, change, coins, 0);

        if (isSuccess) {

            HashMap<Integer, Integer> coinsAndCounts = new HashMap<>();

            for (int i = 0; i < change.size(); i++) {
                int coin = change.get(i);
                if (coinsAndCounts.containsKey(coin)) {
                    int count = (int) coinsAndCounts.get(coin);
                    coinsAndCounts.remove(coin);
                    coinsAndCounts.put(coin, ++count);
                } else {
                    coinsAndCounts.put(coin, 1);
                }
            }

            System.out.print(sum + "-> ");
            Iterator it = coinsAndCounts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                System.out.print(" " + pair.getKey() + "[" + pair.getValue() + "] ");
            }
        } else {
            System.out.println("Can't exchange");
        }


    }

    public static boolean getChange(int sum, ArrayList<Integer> change, ArrayList<Integer> coinsForChange, int i) {
        if (i >= coinsForChange.size()) return false;
        int nominal = coinsForChange.get(i);
        if (sum == nominal) {
            change.add(sum);
            return true;
        }

        if (sum > nominal) {
            change.add(nominal);
            if (getChange(sum - nominal, change, coinsForChange, i) || getChange(sum - nominal, change, coinsForChange, i + 1)) {
                return true;
            }
            change.remove(change.size() - 1);
        }

        return getChange(sum, change, coinsForChange, i + 1);
    }
}
