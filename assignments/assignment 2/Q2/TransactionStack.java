import java.util.NoSuchElementException;

public class TransactionStack {
    private char[] data;
    private int top;
    private int capacity;

    public TransactionStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

        this.capacity = capacity;
        this.data = new char[capacity];
        this.top = -1;
    }

    public void push(char c) {
        if (top == capacity - 1) {
            throw new IllegalStateException("Stack is full.");
        }

        data[++top] = c;
    }

    public char pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        return data[top--];
    }

    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}