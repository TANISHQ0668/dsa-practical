public class RailwayQueue {
    static final int MAX = 5;
    static int[] queue = new int[MAX];
    static int front = -1, rear = -1;

    // Add a customer to the end of the line (Enqueue)
    static void enqueue(int ticketID) {
        if (rear == MAX - 1) {
            System.out.println("Counter line is full! Cannot add Ticket " + ticketID);
            return;
        }
        if (front == -1) front = 0; // First customer arrives
        queue[++rear] = ticketID;
        System.out.println("Customer with Ticket " + ticketID + " joined the line.");
    }

    // Serve the customer at the front of the line (Dequeue)
    static void dequeue() {
        if (front == -1 || front > rear) {
            System.out.println("No customers in line to serve.");
            return;
        }
        System.out.println("Served customer with Ticket " + queue[front++]);

        // Reset pointers when the last customer is served
        if (front > rear) front = rear = -1;
    }

    // Check who is next in line (Peek)
    static void peek() {
        if (front == -1 || front > rear) {
            System.out.println("Line is empty.");
        } else {
            System.out.println("Next to be served: Ticket " + queue[front]);
        }
    }

    // Display all customers currently waiting
    static void display() {
        if (front == -1 || front > rear) {
            System.out.println("Line is empty.");
            return;
        }
        System.out.print("Current Line: ");
        for (int i = front; i <= rear; i++) {
            System.out.print("[Ticket " + queue[i] + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        enqueue(101);
        enqueue(102);
        enqueue(103);
        display();

        peek();
        dequeue();
        display();
    }
}