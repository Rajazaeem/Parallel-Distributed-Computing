public class RequestQueue {
    private char[] data;
    private int front;
    private int rear;
    private int size;

    public RequestQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }

        data = new char[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(char c) {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full.");
        }

        data[rear] = c;
        rear = (rear + 1) % data.length;
        size++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is empty.");
        }

        char c = data[front];
        front = (front + 1) % data.length;
        size--;
        return c;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
