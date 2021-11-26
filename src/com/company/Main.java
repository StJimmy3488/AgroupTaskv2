package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    String m = "";


    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter file name: ");
        String name = sc.nextLine();
        System.out.println("Select disk to search in (ex. c, d, j): ");
        String disc = sc.nextLine();
        System.out.println("please wait...");
        disc += ":\\\\";
        Main m = new Main();
        String path = m.fileFinder(name, new File(disc));
        System.out.println(path);

        Scanner scanner = new Scanner(new File(path));
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String text = scanner.next().toLowerCase();
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

    public String fileFinder(String name, File file) {
        File[] list = file.listFiles();
        if (list != null) {
            for (File fil : list) {
                if (fil.isDirectory()) {
                    fileFinder(name, fil);
                } else if (name.equalsIgnoreCase(fil.getName())) {
                    m = fil.getAbsolutePath();
                }
            }
        }
        return m;

    }
}

