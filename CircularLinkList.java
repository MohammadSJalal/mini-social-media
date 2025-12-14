final public class CircularLinkList {
    private Node <Integer> head ;
    private Node <Integer> tail ;
    public CircularLinkList() {
        head = new Node(0);
        tail = new Node(null);
        head.next = tail;
        tail.next = head;
    }
    public void append(int data) {
        Node newNode = new Node(data);
        if (head.data == 0) {
            tail = newNode;
            tail.next = head;
            head.next = tail;
        }
        else {
            newNode.next = head;
            tail.next = newNode;
            tail = newNode;
        }
        display();
        head.data++;
    }
    public void pop(Integer data) {
        Node <Integer> current = head;
        while (current.next != head && data != current.next.data) {
            current = current.next;
        }
        if (current.next == head) ;
        else current.next = current.next.next;
        display();
        head.data--;
    }
    public void display() {
        Node current = head.next;
        while (current != head) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.println();
    }
    public void customDisplay(int n) {
        Node current = head.next;
        while (n > 0) {
            System.out.print(current.data + ", ");
            current = current.next;
            if (current == head) current = current.next;
            n--;
        }
        System.out.println();
    }
}
