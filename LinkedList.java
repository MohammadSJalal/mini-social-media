
final public class LinkedList <T>{
    private Node <T> head;
    private Node <T> tail;
    private int size;
    private Node <T> iterate;
    private int indexOfIterate;
    public void append(T data) {
        Node <T> d = new Node<>(data);
        if (head == null) {
            head = d;
        }
        else if (tail == null) {
            tail = d;
            head.next = d;
        }
        else {
            tail.next = d;
            tail = d;
        }
        size++;
    }
    private Node<T> getNode(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node <T> c = head;
        for (int i = 0; i < index; i++) {
            c = c.next;
        }
        return c;
    }
    public T get(int index){
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node <T> c = head;
        for (int i = 0; i < index; i++) {
            c = c.next;
        }
        return c.data;
    }
    public void insert(T data , int index) throws IndexOutOfBoundsException {
        Node <T> d = new Node<>(data);
        Node <T> c = null;
        if (index > 0 && index < size) {
            c = getNode(index - 1);
            d.next = c.next;
            c.next = d;
            size++;
        }
        else if (index == 0) {
            d.next = head;
            head = d;
            size++;
        }
        else if (index == size) {
            tail.next = d;
            tail = d;
            size++;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }
    public void remove(T data) throws IllegalArgumentException {
        Node <T> c = head;
        Node <T> p = null;
        while (c != null && c.data != data) {
            p = c;
            c = c.next;
        }
        if (c == null) {
            throw new IllegalArgumentException("List hasn't such data");
        }
        else {
            if (p == null) {
                c = c.next;
                head = c;
            }
            else {
                p.next = c.next;
                if (c == tail) tail = p;
            }
            size--;
        }
    }
    public void pop(int index) throws IndexOutOfBoundsException , IllegalArgumentException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node <T> c = head;
        Node <T> p = null;
        while (c != null && index > 0) {
            index--;
            p = c;
            c = c.next;
        }
        if (c == null) {
            throw new IllegalArgumentException("List hasn't such data");
        }
        else if (p == null) {
            head = c.next;
            c = null;
            size--;
        }
        else {
            p.next = c.next;
            size--;
        }
    }
    public void shrink() {
        if (size == 0) throw new IllegalArgumentException("List is empty");
        else if (size == 1) {
            head = null;
        }
        else if (size == 2) {
            head.next = null;
            tail = null;
        }
        else {
            Node <T> c = getNode(size - 2);
            c.next = null;
            tail = c;
        }
        size--;
    }
    /**
     * this function reverse a singly linked list
     */
    public void reverse(){

        if (size == 1);
        else if (size == 2) {
            head.next.next = head;
            head = head.next;
            head.next.next = null;
        }
        else {
            Node <T> front = head.next.next;
            Node <T> middle = head.next;
            Node <T> back = head;
            back.next = null;
            while (front != null) {
                middle.next = back;
                back = middle;
                middle = front;
                front = front.next;
            }
            middle.next = back;
            head = middle;
        }
    }

    /**
     * since linked list is sorted so duplicate values are next to each other we can simply
     * delete them
     */
    public void deleteDuplicate() {
        Node <T> c = head;
        while (c != null) {
            Node <T> temp = c;
            while (temp != null && temp.data == c.data) {
                temp = temp.next;
            }
            c.next = temp;
            c = c.next;
        }
        display();
    }
    public T findMostDuplicate() {
        T value = null;
        T value2 = null;
        int frequent = 0;
        int frequent2 = 0;
        Node <T> c = head;
        while (c != null) {
            if (c == head) {
                value = c.data;
                frequent++;
            }
            else if (c.data == value) {
                frequent++;
            }
            else if (frequent2 < frequent) {
                value2 = value;
                frequent2 = frequent;
            }
            else {
                value = c.data;
                frequent = 0;
                frequent++;
            }
            c = c.next;
        }
        if (frequent2 < frequent) {
            value2 = value;
            frequent2 = frequent;
        }
        return value2;
    }

    /**
     * this function move next element of List
     * @return if iterate is at the end it false and otherwise true value is return
     */
    public boolean next() {
        if (iterate != null) {
            iterate = iterate.next;
            indexOfIterate++;
            return true;
        }
        return false;
    }

    /**
     * first of all iteration of outside over list you must initiate it since it maybe was somewhere
     * that you won't iterate from there also you can iterate from somewhere you want it is arbitrary
     * but we as default do iterate at index 0
     */
    public void initiateIterate() {
        iterate = head;
        indexOfIterate = 0;
    }

    /**
     * this function give you now iterate where is that's mean if iterate is end of
     * list it return false
     * @return a boolean as it is at end of list or not
     */
    public boolean getStateOfIterate() {
        return iterate != null;
    }
    public void assign (T data) {
        if (iterate == null) {
            iterate = head;
            head.data = data;
            indexOfIterate = 0;
        }
        else {
            iterate.data = data;
        }
    }
    public void iterateGoToPosition(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        iterate = getNode(index);
        indexOfIterate = index;
    }

    /**
     * give access to data that was saved also it give index of data inside the list
     * @return Data , index as array with length 2
     */
    public Object [] showIterate() {
        Object [] list = {iterate.data,indexOfIterate};
        return list;
    }

    /**
     * @return Data of iterate
     */
    public T getIterate() {
        return iterate.data;
    }
    public void display() {
        Node <T> c = head;
        while (c != null) {
            System.out.print(c.data + ", ");
            c = c.next;
        }
        System.out.println();
    }
}

