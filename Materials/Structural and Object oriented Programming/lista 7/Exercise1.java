import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Exercise1 {

    public static void main(String[] args) {

        try (FileReader fr = new FileReader("file.txt");
             BufferedReader br = new BufferedReader(fr)) {

            HashMap<String, Integer> words = new HashMap<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] wordArray = line.split("\\s+"); 

                for (String word : wordArray) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); 
                    if (word.isEmpty()) continue;

                    words.put(word, words.getOrDefault(word, 0) + 1);
                }
            }

            for (String word : words.keySet()) {
                System.out.println("Word: " + word + " Count: " + words.get(word));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
