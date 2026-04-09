
package week2;

import java.util.*;

class AutocompleteSystem {

    private HashMap<String, Integer> frequency = new HashMap<>();

    public void addQuery(String query) {
        frequency.put(query, frequency.getOrDefault(query, 0) + 1);
    }

    public void search(String prefix) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {

            if (entry.getKey().startsWith(prefix)) {
                pq.add(entry);
            }
        }

        int count = 0;

        while (!pq.isEmpty() && count < 10) {

            Map.Entry<String, Integer> result = pq.poll();

            System.out.println((count + 1) + ". " + result.getKey() +
                    " (" + result.getValue() + " searches)");

            count++;
        }
    }

    public void updateFrequency(String query) {
        addQuery(query);
        System.out.println(query + " → Frequency: " + frequency.get(query));
    }
}

public class problem7 {

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("javascript");
        system.addQuery("java download");
        system.addQuery("java tutorial");
        system.addQuery("java tutorial");
        system.addQuery("java 21 features");

        System.out.println("Search Results:");
        system.search("jav");

        System.out.println();
        system.updateFrequency("java 21 features");
    }
}
