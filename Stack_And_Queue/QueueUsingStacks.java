package Stack_And_Queue;

import java.util.*;

class StackQueue {

    // st1 stores newly inserted elements
    // st2 helps provide queue order during pop/peek
    private Stack<Integer> st1, st2;

    // Constructor
    public StackQueue() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    // Insert element into queue
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public void push(int x) {

        // Simply push into st1
        // No shifting required
        st1.push(x);
    }

    // Remove front element of queue
    // Worst Case: O(n)
    // Amortized: O(1)
    public int pop() {

        // If st2 is empty,
        // transfer all elements from st1 to st2
        if (st2.isEmpty()) {

            while (!st1.isEmpty()) {
                st2.push(st1.pop());
            }
        }

        // Queue empty
        if (st2.isEmpty()) {
            return -1;
        }

        // Front element is now on top of st2
        return st2.pop();
    }

    // Get front element without removing it
    // Worst Case: O(n)
    // Amortized: O(1)
    public int peek() {

        // Transfer only when needed
        if (st2.isEmpty()) {

            while (!st1.isEmpty()) {
                st2.push(st1.pop());
            }
        }

        // Queue empty
        if (st2.isEmpty()) {
            return -1;
        }

        // Front element is on top of st2
        return st2.peek();
    }

    // Check if queue is empty
    // Time Complexity: O(1)
    public boolean isEmpty() {

        // Queue is empty only if
        // both stacks are empty
        return st1.isEmpty() && st2.isEmpty();
    }
}