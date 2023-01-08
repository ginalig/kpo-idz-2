package org.example;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Concatenator {
    /**
     * Конкатенация содержимого файлов.
     * @param files Список файлов, которые нужно сконкатенировать.
     * @return Готовая строка.
     * @throws FileNotFoundException
     */
    public static String concatenateFile(List<String> files) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        for (String path : files) {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sb.append("\n" + line);
            }
            scanner.close();
        }
        return sb.toString();
    }

    /**
     * Запись текста в файла
     * @param path Путь к файлу.
     * @param text Текст для записи.
     * @throws IOException
     */
    public static void writeInFile(String path, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(text);
        printWriter.close();
    }
}
