package SlidingWindows;
import java.util.*;

class Solution {

    public int longestNonRepeatingSubstring(String s) {

        // Frequency array to store count of each ASCII character
        int[] hash = new int[256];

        // Left pointer of the sliding window
        int left = 0;

        // Stores the maximum length found so far
        int maxLen = 0;

        // Right pointer expands the window one character at a time
        for (int right = 0; right < s.length(); right++) {

            // Include current character in the window
            // Example: if current character is 'a', hash['a'] becomes 1
            hash[s.charAt(right)]++;

            // If current character appears more than once,
            // shrink the window from the left until it becomes unique again
            while (hash[s.charAt(right)] > 1) {

                // Remove the leftmost character from the window
                hash[s.charAt(left)]--;

                // Move left pointer forward
                left++;
            }

            // Current window [left...right] contains all unique characters
            // Update the maximum length if this window is larger
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

public class LongestSubstring {

    public static void main(String[] args) {

        String input = "cadbzabcd";

        Solution sol = new Solution();

        System.out.println(sol.longestNonRepeatingSubstring(input));
    }
}