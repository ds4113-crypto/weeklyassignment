import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ":" + riskScore;
    }
}

class ClientRiskSorter {

    // 🔹 Bubble Sort (Ascending riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // 🔍 Visualization of each swap
                    System.out.println("Swap: " + Arrays.toString(arr));
                }
            }

            if (!swapped) break; // early stop
        }

        System.out.println("Bubble Sorted (ASC): " + Arrays.toString(arr));
        System.out.println("Total Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (Descending riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("Insertion Sorted (DESC): " + Arrays.toString(arr));
    }

    // 🔹 Comparator: risk DESC, then balance DESC
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.accountBalance, c2.accountBalance);
    }

    // 🔹 Top 10 highest risk clients
    public static void topHighRisk(Client[] arr, int topN) {
        System.out.print("Top " + topN + " High-Risk Clients: ");
        for (int i = 0; i < Math.min(topN, arr.length); i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ") ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 2000),
                new Client("clientB", 50, 3000)
        };

        // 🔹 Bubble Sort (ASC)
        Client[] bubbleArr = clients.clone();
        bubbleSort(bubbleArr);

        // 🔹 Insertion Sort (DESC)
        Client[] insertionArr = clients.clone();
        insertionSort(insertionArr);

        // 🔹 Top High Risk Clients
        topHighRisk(insertionArr, 10);
    }
}