package SlidingWindows;
class Solution {

    public int longestOnes(int[] nums, int k) {

        // Left pointer of the sliding window
        int left = 0;

        // Number of zeros in the current window
        int zeroCount = 0;

        // Stores the maximum valid window size
        int maxLen = 0;

        // Expand the window using the right pointer
        for (int right = 0; right < nums.length; right++) {

            // If current element is 0, increment zero count
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If zeros exceed k, shrink the window
            while (zeroCount > k) {

                // If the left element is 0, remove it from zero count
                if (nums[left] == 0) {
                    zeroCount--;
                }

                // Move left pointer forward
                left++;
            }

            // Current window is valid (contains at most k zeros)
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

public class MaxnoOfOnes {

    public static void main(String[] args) {

        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;

        Solution sol = new Solution();

        int ans = sol.longestOnes(nums, k);

        System.out.println("Maximum Consecutive Ones = " + ans);
    }
}