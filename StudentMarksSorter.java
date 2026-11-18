import java.util.Arrays;
import java.util.Scanner;

public class StudentMarksSorter {

    // Selection Sort: Repeatedly finds the minimum mark and places it at the front
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap minimum element with first unsorted element
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // Insertion Sort: Picks each mark and inserts it into its right spot in the sorted part
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        int[] marks = new int[n];

        System.out.println("Enter student marks:");
        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        while (true) {
            System.out.println("\n--- SORTING MENU ---");
            System.out.println("1. Selection Sort");
            System.out.println("2. Insertion Sort");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }

            // Clone original array so the user can test both sorts on the same input
            int[] sortedMarks = marks.clone();

            switch (choice) {
                case 1:
                    selectionSort(sortedMarks);
                    System.out.println("Sorted Marks (Selection Sort): " + Arrays.toString(sortedMarks));
                    break;
                case 2:
                    insertionSort(sortedMarks);
                    System.out.println("Sorted Marks (Insertion Sort): " + Arrays.toString(sortedMarks));
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}