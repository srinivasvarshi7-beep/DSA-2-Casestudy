
import java.util.Scanner;

public class SegmentTreeInventory {

    static int[] tree;
    static int[] arr;
    static int n;

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    static void update(int node, int start, int end, int idx, int value) {
        if (start == end) {
            arr[idx] = value;
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (idx <= mid)
                update(2 * node, start, mid, idx, value);
            else
                update(2 * node + 1, mid + 1, end, idx, value);

            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    static int query(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return 0;

        if (l <= start && end <= r)
            return tree[node];

        int mid = (start + end) / 2;

        int p1 = query(2 * node, start, mid, l, r);
        int p2 = query(2 * node + 1, mid + 1, end, l, r);

        return p1 + p2;
    }

    static void displayInventory() {
        System.out.println("Current Inventory:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of inventory records: ");
        n = sc.nextInt();

        arr = new int[n];
        tree = new int[4 * n];

        System.out.println("Enter inventory values:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        build(1, 0, n - 1);

        int choice;

        do {
            System.out.println("\n1. Display Inventory");
            System.out.println("2. Range Sum Query");
            System.out.println("3. Update Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayInventory();
                    break;

                case 2:
                    System.out.print("Enter start index: ");
                    int l = sc.nextInt();

                    System.out.print("Enter end index: ");
                    int r = sc.nextInt();

                    int sum = query(1, 0, n - 1, l, r);

                    System.out.println("Inventory Sum = " + sum);
                    break;

                case 3:
                    System.out.print("Enter index to update: ");
                    int idx = sc.nextInt();

                    System.out.print("Enter new value: ");
                    int value = sc.nextInt();

                    update(1, 0, n - 1, idx, value);

                    System.out.println("Inventory Updated Successfully");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
    }
}