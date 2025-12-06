public class MyStack {
    private int top = -1;
    private int [] stack;
    private int capacity;
    public MyStack(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public boolean isFull() {
        return top == capacity - 1;
    }
    public void push(int data) throws StackOverflowError {
        if (isFull()) throw new StackOverflowError();
        stack[++top] = data;
    }
    public int [] getStack() {return stack;}
    public int pop() throws StackOverflowError{
        if (isEmpty()) throw new StackOverflowError();
        return stack[top--];
    }
    public int peek() throws StackOverflowError{
        if (isEmpty()) throw new StackOverflowError();
        return stack[top];
    }
    public void display(){
        for (int i = 0; i < top; i++) {
            System.out.print(stack[i] + ", ");
        }
    }
    public int size(){
        return top + 1;
    }
}
