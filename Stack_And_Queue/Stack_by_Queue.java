package Stack_And_Queue;
import java.util.*;

class Stack_by_Queue {

    Queue<Integer> q;

    public Stack_by_Queue() {
        q = new LinkedList<>();
    }

    // Push element onto stack
    public void push(int x) {

        q.offer(x);

        // Move previous elements behind new element
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());
        }
    }

    // Remove top element
    public int pop() {

        if (q.isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }

        return q.poll();
    }

    // Return top element
    public int top() {

        if (q.isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }

        return q.peek();
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return q.isEmpty();
    }

    public static void main(String[] args) {

        Stack_by_Queue st = new Stack_by_Queue();

        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println(st.top()); // 30
        System.out.println(st.pop()); // 30
        System.out.println(st.top()); // 20
    }
}
