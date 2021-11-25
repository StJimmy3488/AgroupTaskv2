package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("\\C:\\Users\\Ilay\\Desktop\\Random_Book.txt"));

        List<String> list = new ArrayList<>();
        while (sc.hasNext()) {
            String text = sc.next().toLowerCase();
            list.add(text);
        }
        List<String> filteredWords = new ArrayList<>();
        for (String words : list) {
            String sepWords = words.replaceAll("[^\\w\\s]", "");
            filteredWords.add(sepWords);
        }
        Set<String> uniqueWordSet = new HashSet<String>(filteredWords);
        HashMap<String, Integer> wordOccurrencesMap = new HashMap<>();
        for (String uniqueWord : uniqueWordSet) {
            int frequency = Collections.frequency(filteredWords, uniqueWord);
            wordOccurrencesMap.put(uniqueWord, frequency);
        }
        Map<String, Integer> hm = sortByValue(wordOccurrencesMap);
        for (Map.Entry<String, Integer> stringIntegerEntry : hm.entrySet()) {
            System.out.println("Word: " + stringIntegerEntry.getKey() +
                    ", occurrences: " + stringIntegerEntry.getValue());
        }
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> sortedMap) {
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(sortedMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : entryList) {
            temp.put(entry.getKey(), entry.getValue());
        }
        return temp;
    }
}

