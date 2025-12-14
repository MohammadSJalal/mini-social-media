import java.util.EmptyStackException;

final public class DynamicStack <T> {
    Node <T> top;
    public boolean isEmpty() {
        return top == null;
    }
    public void push(T x) {
        Node<T> v = new Node<T>(x);
        v.prev = top;
        top = v;
    }
    public T pop() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        T poped = top.data;
        top = top.prev;
        return poped;
    }
    public T peek() throws EmptyStackException {
        if (isEmpty()) throw new EmptyStackException();
        return top.data;
    }
    public void display() {
        Node <T> v = top;
        while (v != null) {
            System.out.print(v.data + ", ");
            v = v.prev;
        }
        System.out.println();
    }
}