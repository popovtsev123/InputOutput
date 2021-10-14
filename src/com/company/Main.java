package com.company;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File inputFile = new File(getFileName());
        StringBuilder text = new StringBuilder();
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                text.append(scanner.nextLine().replaceAll("\\W", ""));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<Character, Integer> charMap = addToMap(text.toString());
        showResult(charMap);
        addToFile(charMap);
        countWord(inputFile);
    }

    private static void showResult(Map<Character, Integer> charMap) {
        charMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));
    }

    private static Map<Character, Integer> addToMap(String line) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (char letter : line.toCharArray()) {
            if (charMap.containsKey(letter)) {
                charMap.put(letter, charMap.get(letter) + 1);
            } else {
                charMap.put(letter, 1);
            }
        }
        return charMap;
    }

    private static String getFileName() throws IOException {
        System.out.println("Введите имя файла: ");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }

    private static void addToFile(Map<Character, Integer> charMap) throws IOException {
        File outputFile = new File(getFileName());
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        bufferedWriter.write(String.valueOf(charMap));
        bufferedWriter.close();
    }

    private static void countWord(File file) throws IOException {
        InputStream filestream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(filestream);
        BufferedReader reader = new BufferedReader(input);
        String line;
        int n = 1;
        int countWord;
        while ((line = reader.readLine()) !=null) {
            if(!(line.equals(""))) {
                String [] wordlist = line.split("\\s+");
                countWord = wordlist.length;
                System.out.println("Количество слов в строке #" + (n++) + " : " + countWord);
            }
        }
    }
}