import java.util.Arrays;
import java.util.Scanner;

public class ProductPriceSorter {

    // ---------------- MERGE SORT ----------------
    static void mergeSort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);        // Sort left half
            mergeSort(arr, mid + 1, right);   // Sort right half
            merge(arr, left, mid, right);     // Merge sorted halves
        }
    }

    static void merge(double[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        double[] L = new double[n1];
        double[] R = new double[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // ---------------- QUICK SORT ----------------
    static void quickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);   // Sort left of pivot
            quickSort(arr, pi + 1, high);  // Sort right of pivot
        }
    }

    static int partition(double[] arr, int low, int high) {
        double pivot = arr[high]; // Pick last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                double temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        double temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        double[] prices = new double[n];

        System.out.println("Enter product prices (in ₹):");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextDouble();
        }

        while (true) {
            System.out.println("\n--- E-COMMERCE PRICE SORTER ---");
            System.out.println("1. Merge Sort");
            System.out.println("2. Quick Sort");
            System.out.println("3. Compare Both Outputs");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    double[] mergeArr = prices.clone();
                    mergeSort(mergeArr, 0, n - 1);
                    System.out.println("Sorted Prices (Merge Sort): " + Arrays.toString(mergeArr));
                    break;

                case 2:
                    double[] quickArr = prices.clone();
                    quickSort(quickArr, 0, n - 1);
                    System.out.println("Sorted Prices (Quick Sort): " + Arrays.toString(quickArr));
                    break;

                case 3:
                    double[] mArr = prices.clone();
                    double[] qArr = prices.clone();
                    mergeSort(mArr, 0, n - 1);
                    quickSort(qArr, 0, n - 1);

                    System.out.println("Original Prices : " + Arrays.toString(prices));
                    System.out.println("Merge Sort      : " + Arrays.toString(mArr));
                    System.out.println("Quick Sort      : " + Arrays.toString(qArr));
                    System.out.println("Outputs Match?  : " + Arrays.equals(mArr, qArr));
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
        sc.close();
    }
}