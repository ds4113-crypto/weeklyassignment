import java.util.Arrays;

class RiskThresholdLookup {

    // 🔍 Linear Search (Unsorted)
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                System.out.println("Linear Search: Found at index " + i);
                break;
            }
        }

        if (!found) {
            System.out.println("Linear Search: Not Found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // 🔎 Binary Search - Floor
    public static int findFloor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int floor = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return floor;
    }

    // 🔎 Binary Search - Ceiling
    public static int findCeiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int ceil = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) {
                return arr[mid];
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }
        return ceil;
    }

    // 🔎 Binary Search - Insertion Point (Lower Bound)
    public static int insertionPoint(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // insertion index
    }

    // Counter class
    static class Counter {
        int count = 0;
    }

    // 🚀 Main Method
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // 🔍 Linear Search (unsorted example)
        linearSearch(risks, target);

        // 🔃 Sort array for Binary Search
        Arrays.sort(risks);

        Counter counter = new Counter();

        int floor = findFloor(risks, target, counter);
        int ceil = findCeiling(risks, target, counter);
        int index = insertionPoint(risks, target, counter);

        System.out.println("\nBinary Search Results:");
        System.out.println("Floor: " + floor);
        System.out.println("Ceiling: " + ceil);
        System.out.println("Insertion Index: " + index);
        System.out.println("Comparisons: " + counter.count);
    }
}