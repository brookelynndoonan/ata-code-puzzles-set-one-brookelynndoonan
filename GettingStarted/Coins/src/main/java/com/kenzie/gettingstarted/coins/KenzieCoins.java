package com.kenzie.gettingstarted.coins;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Use this to explore solutions to the Pair-Sum interview problem.
 * <p>
 * Kenzie uses a virtual currency, Kenzie Coins, in the Kenzie App Store. When I purchased a Kenzie App,
 * I received some Kenzie Coins. I've bought a few apps, but now I have a balance of 21 coins left
 * over, and it really bothers me.
 * <p>
 * No one app costs 21 coins. But maybe I could find an app for 15 coins, and another app for 6 coins.
 * <p>
 * Of course, this is a problem for many people, with many different balances. The balance isn't
 * always 21 coins, it could be anything.
 * <p>
 * Write a method to help me zero out my balance with two apps.
 */
public class KenzieCoins {
    /*
       Notes: Just like in the guided classroom examples, if you change the name of these methods
       you must also update the KenzieCoinsTest in com.kenzie.gettingstarted.coins
     */

    /**
     * Determine *whether* any two values in the provided list adds up to the provided balance.
     * <p>
     * The KenzieCoinsTest includes some sample data to check your solution.
     *
     * @param balance The balance to find a pair for.
     * @param values  The values to search. Never null, contains no null values. May contain duplicates.
     * @return True if any two values sum to the provided balance, false otherwise.
     */
    public boolean anyPairSumsToBalance(int balance, List<Integer> values) {
        if (values == null) {
            return false;
        }
        for (int pairValue1 = 0; pairValue1 < values.size(); pairValue1++) {
            for (int pairValue2 = pairValue1 + 1; pairValue2 < values.size(); pairValue2++)

                if (values.get(pairValue1) + values.get(pairValue2) == balance) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Find all the pairs of values that add up to the provided balance.
     * <p>
     * The KenzieCoinsTest includes some sample data to check your solution.
     *
     * @param balance The balance to find a pair for.
     * @param values  The values to search. Never null, contains no null values. May contain duplicates.
     * @return A list of pairs of numbers from `values` that sum to the `balance`.
     */
    public List<Pair> findPairsThatSumToBalance(int balance, List<Integer> values) {
        List<Pair> duoNumbers = new ArrayList<>();

        for (int pairValue1 = 0; pairValue1 < values.size(); pairValue1++) {
            for (int pairValue2 = pairValue1 + 1; pairValue2 < values.size(); pairValue2++) {
                if (values.get(pairValue1) + values.get(pairValue2) == balance) {
                    duoNumbers.add(new Pair(values.get(pairValue1), values.get(pairValue2)));
                }
            }
        }
        return duoNumbers;
    }

}
