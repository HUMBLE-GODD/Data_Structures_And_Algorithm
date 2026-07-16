package Stack_And_Queue;
import java.util.*;

class ArrayQueue {

    private int[] arr;
    private int front;
    private int rear;

    public ArrayQueue(int size) {
        arr = new int[size];
        front = -1;
        rear = -1;
    }

    public void enqueue(int x) {

        if (rear == arr.length - 1) {
            System.out.println("Queue Overflow");
            return;
        }

        if (front == -1)
            front = 0;

        arr[++rear] = x;
    }

    public int dequeue() {

        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }

        return arr[front++];
    }

    public int peek() {

        if (isEmpty()) {
            System.out.println("Queue is Empty");
            return -1;
        }

        return arr[front];
    }

    public boolean isEmpty() {
        return front == -1 || front > rear;
    }

    public static void main(String[] args) {

        ArrayQueue q = new ArrayQueue(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Front: " + q.peek());
        System.out.println("Removed: " + q.dequeue());
        System.out.println("Front: " + q.peek());
    }
}