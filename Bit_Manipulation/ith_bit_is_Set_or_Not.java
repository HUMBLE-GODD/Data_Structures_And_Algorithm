package Bit_Manipulation;
class Solution {

    // Function to check whether the i-th bit of n is set (1) or not
    public boolean checkIthBit(int n, int i) {

        // Create a mask by shifting 1 to the left i times.
        // Example:
        // i = 0 -> 0001
        // i = 1 -> 0010
        // i = 2 -> 0100
        // i = 3 -> 1000
        int mask = (1 << i);

        // Perform bitwise AND between n and the mask.
        // If the i-th bit in n is 1, the result will be non-zero.
        // If the i-th bit in n is 0, the result will be 0.
        return (n & mask) != 0;
    }
}

public class ith_bit_is_Set_or_Not {
    public static void main(String[] args) {

        Solution sol = new Solution();

        int num = 5;      // Binary: 101
        int bitIndex = 2; // Check the 2nd bit

        // Call the function to check the bit
        if (sol.checkIthBit(num, bitIndex)) {
            System.out.println("The " + bitIndex +
                               "-th bit of " + num +
                               " is set (1).");
        } else {
            System.out.println("The " + bitIndex +
                               "-th bit of " + num +
                               " is not set (0).");
        }
    }
}