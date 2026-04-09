import java.util.Arrays;

class AccountLookup {

    // 🔍 Linear Search
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First Occurrence: " + first);
        System.out.println("Last Occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
    }

    // 🔎 Binary Search - First Occurrence
    public static int firstOccurrence(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int first = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                first = mid;
                high = mid - 1; // move left
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    // 🔎 Binary Search - Last Occurrence
    public static int lastOccurrence(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int last = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                last = mid;
                low = mid + 1; // move right
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    // Counter class to track comparisons
    static class Counter {
        int count = 0;
    }

    // 🚀 Main Method
    public static void main(String[] args) {

        String[] arr = {"accB", "accA", "accB", "accC"};
        String target = "accB";

        // 🔍 Linear Search
        linearSearch(arr, target);

        // 🔃 Sort array for Binary Search
        Arrays.sort(arr);

        Counter counter = new Counter();

        int first = firstOccurrence(arr, target, counter);
        int last = lastOccurrence(arr, target, counter);

        int count = (first == -1) ? 0 : (last - first + 1);

        System.out.println("\nBinary Search:");
        System.out.println("First Occurrence: " + first);
        System.out.println("Last Occurrence: " + last);
        System.out.println("Count: " + count);
        System.out.println("Comparisons: " + counter.count);
    }
}