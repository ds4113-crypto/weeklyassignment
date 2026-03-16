
import java.util.*;

class PlagiarismDetector {

    private HashMap<String, Set<String>> index = new HashMap<>();
    private int n = 5;

    public void addDocument(String docId, String text) {

        List<String> words = Arrays.asList(text.toLowerCase().split("\\s+"));

        for (int i = 0; i <= words.size() - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words.get(i + j)).append(" ");
            }

            String ngram = gram.toString().trim();

            index.putIfAbsent(ngram, new HashSet<>());
            index.get(ngram).add(docId);
        }
    }

    public void analyzeDocument(String docId, String text) {

        List<String> words = Arrays.asList(text.toLowerCase().split("\\s+"));

        HashMap<String, Integer> matchCount = new HashMap<>();
        int total = 0;

        for (int i = 0; i <= words.size() - n; i++) {

            StringBuilder gram = new StringBuilder();

            for (int j = 0; j < n; j++) {
                gram.append(words.get(i + j)).append(" ");
            }

            String ngram = gram.toString().trim();
            total++;

            if (index.containsKey(ngram)) {

                for (String doc : index.get(ngram)) {

                    matchCount.put(doc, matchCount.getOrDefault(doc, 0) + 1);
                }
            }
        }

        System.out.println("Extracted " + total + " n-grams");

        for (Map.Entry<String, Integer> entry : matchCount.entrySet()) {

            double similarity = (entry.getValue() * 100.0) / total;

            System.out.println("Found " + entry.getValue() + " matching n-grams with " + entry.getKey());
            System.out.println("Similarity: " + similarity + "%");
        }
    }
}

public class problem4 {

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        String doc1 = "data structures and algorithms are important for computer science students";
        String doc2 = "data structures and algorithms are very important topics in computer science";

        detector.addDocument("essay_089.txt", doc1);

        detector.analyzeDocument("essay_123.txt", doc2);
    }
}
