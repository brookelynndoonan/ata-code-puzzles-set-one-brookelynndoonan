package com.kenzie.expandingwindow.minimumwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains a problem that can be solved using the Expanding Window Technique.
 */
public class MinimumWindowSubstring {

    /**
     * Given two strings s and t, return the minimum window substring of s such that every character in
     * t (including duplicates) is included in the window. If there is no such substring, return the
     * empty string "".
     *
     * Example:
     *   s = "ADOBECODEBANC"
     *   t = "ABC"
     *
     *   result = "BANC"
     *
     * @param s - the string from which to identify the shortest substring where each character in t appears.
     * @param t - the string containing all the characters that must appear in the substring from s.
     * @return the shortest substring of s in which each character in t appears.
     */
        public static String minimumWindowSubstring(String s, String t) {

            if (s == null || t == null) {
                return "";
            }
            if (s.length() < t.length()) {
                return "";
            }

            Map<Character, Integer> howOften = new HashMap<>();
            for (char c : t.toCharArray()) {
                howOften.put(c, howOften.getOrDefault(c, 0) + 1);
            }

            Map<Character, Integer> mapForS = new HashMap<>();
            int left = 0, right = 0;
            int minimumLength = Integer.MAX_VALUE;
            int minimumStart = 0;
            int requiredCharacters = howOften.size();
            int batch = 0;

            while (right < s.length()) {
                char rightChar = s.charAt(right);

                mapForS.put(rightChar, mapForS.getOrDefault(rightChar, 0) + 1);

                if (howOften.containsKey(rightChar) && mapForS.get(rightChar).intValue() ==
                        howOften.get(rightChar).intValue()) {
                    batch++;
                }

                while (left <= right && batch == requiredCharacters) {
                    char leftChar = s.charAt(left);

                    if (right - left + 1 < minimumLength) {
                        minimumLength = right - left + 1;
                        minimumStart = left;
                    }

                    mapForS.put(leftChar, mapForS.get(leftChar) - 1);

                    if (howOften.containsKey(leftChar) && mapForS.get(leftChar)
                            < howOften.get(leftChar)) {
                        batch--;
                    }

                    left++;
                }

                right++;
            }

            if (minimumLength == Integer.MAX_VALUE) {
                return "";
            }

            return s.substring(minimumStart, minimumStart + minimumLength);
        }
}
