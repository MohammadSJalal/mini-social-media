import java.util.EmptyStackException;
final public class DoubleLinkedList <T> {
    private Node <T> Nil;
    private Node <T> iterate;
    private int size;
    public DoubleLinkedList() {
        Nil = new Node<>(null);
    }
    public void append(T data) {
        Node <T> D = new Node<>(data);
        if (size == 0){
            Nil.next = D;
            Nil.prev = D;
            D.next = Nil;
            D.prev = Nil;
        }
        else {
            Nil.prev.next = D;
            D.prev = Nil.prev;
            D.next = Nil;
            Nil.prev = D;
        }
        size++;
        display();
    }
    public void shrink() throws IndexOutOfBoundsException{
        if (size == 0) throw new IllegalStateException("List is empty");
        else if (size == 1){
            Nil.next = null;
            Nil.prev = null;
        }
        else {
            Node <T> T = Nil.prev;
            Nil.prev.prev.next = Nil;
            Nil.prev = Nil.prev.prev;
            T = null;
        }
        size--;
        display();
    }
    public int size() {
        return size;
    }
    public T get(int index) throws IndexOutOfBoundsException {
        return getNode(index).data;
    }
    public Node<T> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node <T> c;
        if (index > size/2) {
            c = Nil.prev;
            for (int i = index - size ; i < -1; i++) {
                c = c.prev;
            }
        }
        else {
            c = Nil.next;
            for (int i = index ; i > 0; i--) {
                c = c.next;
            }
        }
        return c;
    }
    public void insert(T data, int index) throws IndexOutOfBoundsException {
        Node <T> d = new Node<>(data);
        Node <T> c = getNode(index);
        d.next = c;
        d.prev = c.prev;
        c.prev.next = d;
        c.prev = d;
        size++;
        display();
    }
    public void remove(T value) throws IndexOutOfBoundsException {
        Node <T> c = Nil.next;
        while (c != Nil && !value.equals(c.data)) {
            c =c.next;
        }
        if (c == Nil) throw new IllegalArgumentException("such value does not exist");
        c.prev.next = c.next;
        c.next.prev = c.prev;
        c = null;
        size--;
        display();
    }
    public void pop(int index) throws IndexOutOfBoundsException {
        Node <T> c = getNode(index);
        c.prev.next = c.next;
        c.next.prev = c.prev;
        c = null;
        size--;
        display();
    }
    public void InitiateIterate(){
        Node <T> iterate = Nil.next;
    }
    public void InitiateIterate(int index) throws IndexOutOfBoundsException {
        Node <T> iterate = getNode(index);
    }
    public void next() throws IndexOutOfBoundsException {
        if (iterate == Nil) throw new IllegalStateException("we reached the end of the list");
        iterate = iterate.next;
    }
    public void previous() throws IndexOutOfBoundsException {
        if (iterate == Nil) throw new IllegalStateException("we reached the head of the list");
        iterate = iterate.prev;
    }
    public T getData() throws IndexOutOfBoundsException {
        return iterate.data;
    }
    public void changeData(T newData) throws IndexOutOfBoundsException {
        iterate.data = newData;
    }
    public void display() {
        Node <T> c = Nil.next;
        if (c == null) return;
        while (c != Nil) {
            System.out.print(c.data+", ");
            c = c.next;
        }
        System.out.println();
    }
}

