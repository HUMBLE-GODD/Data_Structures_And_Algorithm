package Stack_And_Queue;
import java.util.*;

class StackQueue {
    
    // st1 always stores queue elements
    // st2 is used temporarily during push
    private Stack<Integer> st1, st2;

    // Constructor
    public StackQueue() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    // Insert element into queue
    public void push(int x) {

        // Move all elements from st1 to st2
        // so that st1 becomes empty
        while (!st1.isEmpty()) {
            st2.push(st1.pop());
        }

        // Insert new element into empty st1
        st1.push(x);

        // Move everything back from st2 to st1
        // This keeps the oldest element at the top
        while (!st2.isEmpty()) {
            st1.push(st2.pop());
        }
    }

    // Remove front element of queue
    public int pop() {

        // Queue is empty
        if (st1.isEmpty()) {
            return -1;
        }

        // Top of st1 is always queue front
        return st1.pop();
    }

    // Get front element without removing it
    public int peek() {

        if (st1.isEmpty()) {
            return -1;
        }

        // Front element is on top
        return st1.peek();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return st1.isEmpty();
    }
}