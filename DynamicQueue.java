import java.util.EmptyStackException;

final public class DynamicQueue <T> {
    Node <T> front , rear;
    public DynamicQueue() {
        front = rear = null;
    }
    public boolean isEmpty() {
        return front == null && rear == null;
    }
    public void enqueue (T data) {
        if (isEmpty()) {
            front = new Node<T>(data);
        }
        else if (front != null && rear == null) {
            rear = new Node<T>(data);
            front.next = rear;
        }
        else {
            Node<T> value = new Node<T>(data);
            rear.next = value;
            rear = value;
        }
        display();
    }
    public T dequeue() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        T d = front.data;
        if (rear == front){
            front = rear = null;
        }
        else {
            front = front.next;
        }
        display();
        return d;
    }
    public void display() {
        Node current = front;
        while (current != null) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.println();
    }
    public T peak() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return front.data;
    }
}
