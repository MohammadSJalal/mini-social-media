public class PriorityQueue {
    private int capacity , numberOfPriorities , size;
    private Queue[] queueOfPriorities ;
    public PriorityQueue(int capacity , int numberOfPriorities) {
        this.capacity = capacity;
        this.size = 0;
        this.numberOfPriorities = numberOfPriorities;
        this.queueOfPriorities = new Queue[numberOfPriorities];
        for (int i = 0; i < numberOfPriorities; i++) {
            this.queueOfPriorities[i] = new Queue(capacity);
        }
    }
    public void add(int num,int priority) throws IndexOutOfBoundsException , IllegalArgumentException {
        if (size == capacity) throw new IndexOutOfBoundsException();
        if (0 > priority || priority >= numberOfPriorities) throw new IllegalArgumentException();
        queueOfPriorities[priority].enqueue(num);
        size++;
    }
    public int remove() throws IllegalStateException{
        int i = 0;
        if (size == 0) throw new IllegalStateException("Queue is empty");
        while(i < numberOfPriorities && queueOfPriorities[i].isEmpty()) {
            i++;
        }
        size--;
        return queueOfPriorities[i].dequeue();
    }
    public int peek() throws IllegalStateException{
        if (size == 0) throw new IllegalStateException("Queue is empty");
        int i = 0;
        while(i < numberOfPriorities && queueOfPriorities[i].isEmpty()) {
            i++;
        }
        return queueOfPriorities[i].peekFront();
    }
}
