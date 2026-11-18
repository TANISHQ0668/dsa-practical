public class PrinterCircularQueue {
    static final int MAX = 4;
    static int[] queue = new int[MAX];
    static int front = -1, rear = -1;

    // Add a new print job (Enqueue)
    static void enqueue(int jobID) {
        if ((rear + 1) % MAX == front) {
            System.out.println("Print queue is full! Cannot add Job " + jobID);
            return;
        }
        if (front == -1) front = 0; // First job added
        rear = (rear + 1) % MAX;    // Wrap around to the start if needed
        queue[rear] = jobID;
        System.out.println("Print Job " + jobID + " added to queue.");
    }

    // Complete the print job at the front (Dequeue)
    static void dequeue() {
        if (front == -1) {
            System.out.println("No print jobs in queue.");
            return;
        }
        System.out.println("Completed Print Job " + queue[front]);
        
        if (front == rear) {
            front = rear = -1;      // Queue becomes empty
        } else {
            front = (front + 1) % MAX; // Move front circularly
        }
    }

    // View the next print job (Peek)
    static void peek() {
        if (front == -1) {
            System.out.println("Print queue is empty.");
        } else {
            System.out.println("Next to print: Job " + queue[front]);
        }
    }

    // Display all pending print jobs
    static void display() {
        if (front == -1) {
            System.out.println("Print queue is empty.");
            return;
        }
        System.out.print("Pending Jobs: ");
        int i = front;
        while (true) {
            System.out.print("[Job " + queue[i] + "] ");
            if (i == rear) break;
            i = (i + 1) % MAX;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        enqueue(1);
        enqueue(2);
        enqueue(3);
        enqueue(4);
        display();

        dequeue(); // Frees index 0
        dequeue(); // Frees index 1

        enqueue(5); // Reuses index 0
        enqueue(6); // Reuses index 1
        display();
        peek();
    }
}