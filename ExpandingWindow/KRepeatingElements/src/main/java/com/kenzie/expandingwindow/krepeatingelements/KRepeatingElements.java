package com.kenzie.expandingwindow.krepeatingelements;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class KRepeatingElements {

    /**
     * Given a string s and an integer k, return the length of the longest substring of s such that the
     * frequency of each character in this substring is greater than or equal to k.
     * <p>
     * Example:
     * s = aaabb
     * k = 3
     * <p>
     * result = aaa
     *
     * @param s - the string from which to identify the longest substring where each character appears
     *          at least k times. s will contain only lowercase letters.
     * @param k - the minimum frequency which each character should appear in the substring. k will be
     *          a postive number.
     * @return the length of the longest substring of s where each character appears at least k times.
     */

    //Used alphabet approach I saw in reading, I researched various websites for ideas and this one
    //with the alphabet made the most sense to me.
    //cited ideas from this site
    //https://www.geeksforgeeks.org/longest-substring-where-all-the-characters-appear-at-least-k-times-set-3/

    public static int kRepeatingElements(String s, int k) {
        return longestSubstring(s, k, 0, s.length() - 1);
    }

    private static int longestSubstring(String s, int k, int start, int end) {
        if (end - start + 1 < k) {
            return 0;
        }

        int[] charCount = new int[26];

        for (int i = start; i <= end; i++) {
            charCount[s.charAt(i) - 'a']++;
        }

        for (int i = start; i <= end; i++) {
            if (charCount[s.charAt(i) - 'a'] < k) {
                int left = longestSubstring(s, k, start, i - 1);
                int right = longestSubstring(s, k, i + 1, end);
                return Math.max(left, right);
            }
        }

        return end - start + 1;
    }
}






