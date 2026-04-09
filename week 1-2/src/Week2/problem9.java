
package week2;

import java.util.*;

class Transaction {
    int id;
    int amount;
    String merchant;
    String account;
    long time;

    Transaction(int id, int amount, String merchant, String account, long time) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.account = account;
        this.time = time;
    }
}

class TransactionAnalyzer {

    public void findTwoSum(List<Transaction> transactions, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);
                System.out.println("Two-Sum Pair: (" + other.id + ", " + t.id + ")");
            }

            map.put(t.amount, t);
        }
    }

    public void findTwoSumWithTime(List<Transaction> transactions, int target, long window) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : transactions) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {
                Transaction other = map.get(complement);

                if (Math.abs(t.time - other.time) <= window) {
                    System.out.println("Time Window Pair: (" + other.id + ", " + t.id + ")");
                }
            }

            map.put(t.amount, t);
        }
    }

    public void detectDuplicates(List<Transaction> transactions) {

        HashMap<String, List<Transaction>> map = new HashMap<>();

        for (Transaction t : transactions) {

            String key = t.amount + "-" + t.merchant;

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(t);
        }

        for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {

            if (entry.getValue().size() > 1) {

                System.out.println("Duplicate Transactions:");

                for (Transaction t : entry.getValue()) {
                    System.out.println("ID: " + t.id + " Account: " + t.account);
                }
            }
        }
    }
}

public class problem9 {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction(1, 500, "StoreA", "acc1", 1000));
        transactions.add(new Transaction(2, 300, "StoreB", "acc2", 1100));
        transactions.add(new Transaction(3, 200, "StoreC", "acc3", 1200));
        transactions.add(new Transaction(4, 500, "StoreA", "acc4", 1300));

        TransactionAnalyzer analyzer = new TransactionAnalyzer();

        analyzer.findTwoSum(transactions, 500);

        analyzer.findTwoSumWithTime(transactions, 500, 200);

        analyzer.detectDuplicates(transactions);
    }
}
