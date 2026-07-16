package Stack_And_Queue;
import java.util.*;
class Array_to_Stack
{
private int [] arr;
private int top;

Array_to_Stack(int size){
    arr = new int [size];
    top = -1;
}
void push(int x){
    if (top == arr.length-1){
        System.out.println("Stack Overflow");
        return;
    }
    arr[++top] = x;
}
int pop(){
    if(isEmpty()){
        System.out.println("Stack Underflow");
        return -1;
    }
    return arr[top--];

}
public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return arr[top];
    }

    
    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {

        Array_to_Stack stack = new Array_to_Stack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Top: " + stack.peek());
    }
}



