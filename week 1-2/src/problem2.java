import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class FlashSaleManager {

    private HashMap<String, Integer> stock = new HashMap<>();
    private HashMap<String, LinkedHashMap<Integer, Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedHashMap<>());
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public synchronized void purchaseItem(String productId, int userId) {

        int available = stock.getOrDefault(productId, 0);

        if (available > 0) {
            stock.put(productId, available - 1);
            System.out.println("Success for user " + userId + ", " + (available - 1) + " units remaining");
        } else {
            LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);
            int position = queue.size() + 1;
            queue.put(userId, position);
            System.out.println("Added to waiting list, position #" + position);
        }
    }

    public void showWaitingList(String productId) {
        LinkedHashMap<Integer, Integer> queue = waitingList.get(productId);

        for (Map.Entry<Integer, Integer> entry : queue.entrySet()) {
            System.out.println("User " + entry.getKey() + " Position #" + entry.getValue());
        }
    }
}

public class problem2 {

    public static void main(String[] args) {

        FlashSaleManager manager = new FlashSaleManager();

        manager.addProduct("IPHONE15_256GB", 100);

        System.out.println("Stock Available: " + manager.checkStock("IPHONE15_256GB") + " units");

        manager.purchaseItem("IPHONE15_256GB", 12345);
        manager.purchaseItem("IPHONE15_256GB", 67890);

        for (int i = 1; i <= 100; i++) {
            manager.purchaseItem("IPHONE15_256GB", 10000 + i);
        }

        manager.purchaseItem("IPHONE15_256GB", 99999);

        System.out.println("Waiting List:");
        manager.showWaitingList("IPHONE15_256GB");
    }
}
