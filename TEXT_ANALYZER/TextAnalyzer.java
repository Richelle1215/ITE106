import java.io.*;
import java.util.*;

public class textanayzer {
    public static void main(String[] args) {
        try {
            File file = new File("hello.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder text = new StringBuilder();
            String line;
            int lineNumber = 1; 
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
                
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
            reader.close();

         
            String cleanText = text.toString().replaceAll("[^a-zA-Z0-9\\s]", "");

            String[] words = cleanText.split("\\s+");
            int wordCount = words.length;

          
            String[] sentences = cleanText.split("[.!?]"); 
            int sentenceCount = sentences.length;

           
            String upperCaseText = cleanText.toUpperCase(); 

            
            String longestWord = "";
            for (String word : words) {
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }

          
            Map<String, Integer> wordFrequencies = new HashMap<>();
            for (String word : words) {
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
            }


            System.out.println("\nWord Count: " + wordCount);
            System.out.println("Sentence Count: " + sentenceCount);
            System.out.println("Uppercase Text: " + upperCaseText);
            System.out.println("Longest Word: " + longestWord);
            System.out.println("Word Frequencies: " + wordFrequencies);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
