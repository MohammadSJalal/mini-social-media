import java.util.Arrays;
import java.util.Stack;

public class Queue {
    private int front, rear, capacity, size;
    private boolean withoutDuplicatedValue;
    private short reverseOrder;
    private int[] queue;

    Queue(int capacity) {
        this.capacity = capacity + 1;
        this.front = 0;
        this.rear = 0;
        this.queue = new int[this.capacity];
        reverseOrder = 1;
        size = 0;
        withoutDuplicatedValue = false;
    }

    Queue(int capacity, boolean withoutDuplicatedValue) {
        this.capacity = capacity + 1;
        this.front = 0;
        this.rear = 0;
        this.queue = new int[this.capacity];
        reverseOrder = 1;
        size = 0;
        this.withoutDuplicatedValue = withoutDuplicatedValue;
    }

    public boolean isFull() {
        return size == capacity - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getSize() {
        return size;
    }

    public int getReverseOrder() {
        return reverseOrder;
    }

    public void enqueue(int item) {
        if (isFull()) throw new IllegalStateException("Queue is full");
        if (withoutDuplicatedValue) {
            Integer[] value = search(item);
            if (value != null) {
                return;
            }
        }
        queue[rear] = item;
        increment(false);
        size += 1;
        System.out.print("enqueue operation : ");
        display();
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        int item = queue[front];
        increment(true);
        size -= 1;
        System.out.print("Dequeue operation : ");
        display();
        return item;
    }

    public int peekFront() {
        return queue[front];
    }

    public void reverse() {
        reverseOrder *= -1;
        int temp = front;
        front = rear + reverseOrder;
        rear = temp + reverseOrder;
        correctnessValue(true);
        correctnessValue(false);
        System.out.print("reverse operation : ");
        display();
    }

    public void removeDuplicatesSlow() {
        Integer[] newQueue = new Integer[capacity];
        int index = 0;
        for (int i = front; ; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            if (i == front) {
                newQueue[index++] = queue[i];
            }
            for (int j = 0; j < index; j++) {
                if (newQueue[j].equals(queue[i])) {
                    break;
                }
                if (j == index - 1) {
                    newQueue[index++] = queue[i];
                }
            }
        }
        for (int i = 0; i <= index; i++) {
            queue[i] = newQueue[i];
        }
        front = 0;
        rear = index;
        reverseOrder = 1;
    }

    //fastest remove duplicates function
    public void removeDuplicates() {
        if (isEmpty()) return;

        Integer[] maxAndMin = findInterval();
        int max = maxAndMin[0];
        int min = maxAndMin[1];

        int offset = (min < 0) ? -min : 0;
        boolean[] duplicates = new boolean[max + offset + 1];

        int[] newQueue = new int[capacity];
        int index = 0;

        for (int i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            int value = queue[i];
            int mapped = value + offset;

            if (!duplicates[mapped]) {
                duplicates[mapped] = true;
                newQueue[index++] = value;
            }
        }

        front = 0;
        rear = index;
        size = index;
        reverseOrder = 1;

        for (int i = 0; i < index; i++) {
            queue[i] = newQueue[i];
        }

        display();
    }

    public Integer[] findInterval() {
        Integer[] values = new Integer[2];
        for (int i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == front) {
                values[0] = queue[i];
                values[1] = queue[i];
            }
            if (i == rear) break;
            if (values[0] < queue[i]) values[0] = queue[i];
            if (values[1] > queue[i]) values[1] = queue[i];
        }
        return values;
    }

    public Integer[] search(int item) {
        Integer[] temp = new Integer[2];
        for (int i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            if (item == queue[i]) {
                temp[0] = queue[i];
                temp[1] = i;
                return temp;
            }
        }
        return null;
    }

    public void deleteElement(int item) {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        if (size == 1 && item == queue[front]) {
            front = 0;
            rear = 0;
            size = 0;
            reverseOrder = 1;
            System.out.print("delete operation : ");
            display();
            return;
        }
        int numberOfOperate = 0, i;
        boolean shiftToFront = false;
        for (i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            if (item == queue[i]) {
                if (numberOfOperate <= Size() / 2) {
                    shiftToFront = true;
                } else {
                    numberOfOperate = Size() - numberOfOperate;
                }
                break;
            }
            numberOfOperate++;
        }
        while (numberOfOperate > 0) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (shiftToFront) {
                queue[i] = queue[correct(i - reverseOrder)];
                i -= reverseOrder;
            } else {
                queue[i] = queue[correct(i + reverseOrder)];
                i += reverseOrder;
            }
            numberOfOperate--;
        }
        if (shiftToFront) {
            front += reverseOrder;
            correctnessValue(true);
        } else {
            rear -= reverseOrder;
            correctnessValue(false);
        }
        size--;
        System.out.print("delete operation : ");
        display();
    }

    public int getSizeWithoutNumerateDublicates() {
        if (isEmpty()) return 0;


        int sizeW = 0;
        Integer[] maxAndMin = findInterval();
        int max = maxAndMin[0];
        int min = maxAndMin[1];

        int offset = (min < 0) ? -min : 0;
        boolean[] duplicates = new boolean[max + offset + 1];

        for (int i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            int value = queue[i];
            int mapped = value + offset;

            if (!duplicates[mapped]) {
                duplicates[mapped] = true;
                sizeW++;
            }
        }
        return sizeW;
    }

    public int getValue(int index) {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        int position = front + index * reverseOrder;
        if (position >= capacity) position -= capacity;
        else if (position < 0) position += capacity;
        return queue[position];
    }

    public boolean isEqual(Queue q) {
        if (q.getSize() != this.getSize()) return false;
        int i = 0;
        while (i < this.getSize()) {
            if (q.getValue(i) != this.getValue(i)) return false;
            i++;
        }
        return true;
    }

    public void increment(boolean Front) {
        if (Front) {
            front += reverseOrder;
            correctnessValue(Front);
        } else {
            rear += reverseOrder;
            correctnessValue(Front);
        }
    }

    public int Size() {
        int len = rear - front;
        if (len == 0 && isEmpty()) return 0;
        if (len * reverseOrder < 0) return capacity - Math.abs(len);
        else return Math.abs(len);
    }

    public int correct(int item) {
        if (item == capacity) return 0;
        else if (item < 0) return capacity - 1;
        else return item;
    }

    public void correctnessValue(boolean Front) {
        if (Front) {
            if (front == capacity) front = 0;
            else if (front < 0) front = capacity - 1;
        } else {
            if (rear == capacity) rear = 0;
            else if (rear < 0) rear = capacity - 1;
        }
    }

    public void eliminateEvenSum() {
        MyStack s = new MyStack(capacity);
        for (int i = front; i != rear; i += reverseOrder) {
            if (i == capacity) i = 0;
            if (i < 0) i = capacity - 1;
            if (i == rear) break;
            if (i == front) {
                s.push(queue[i]);
            } else if ((s.peek() + queue[i]) % 2 == 0) {
                s.pop();
            } else {
                s.push(queue[i]);
            }
        }
        int[] values = s.getStack();
        for (int i = 0; i < s.size(); i++) {
            queue[i] = values[i];
        }
        front = 0;
        rear = s.size();
        correctnessValue(false);
        reverseOrder = 1;
        size = s.size();
    }

    public void display() {
        int i = front;
        while (i != rear) {
            System.out.print(queue[i] + ", ");
            i += reverseOrder;
            if (i == capacity) i = 0;
            else if (i < 0) i = capacity - 1;
        }
        System.out.println();
    }
}