package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    final static String root = "your root path";
    final static String outputPath = "your output path";
    public static void main(String[] args) throws FileNotFoundException {
        DependenciesGraph dependenciesGraph = new DependenciesGraph(root);

        Set<String> cycle = dependenciesGraph.getCycle();
        if (cycle != null) {
            System.out.println("Цикл между зависимостями обнаружен в файлах: ");
            for (String s : cycle) {
                System.out.println(s);
            }
            return;
        }
        try {
            System.out.println("Список файлов: ");
            dependenciesGraph.printFilesInOrder();
        } catch (Exception e) {
            System.out.println("Неверный формат зависимостей");
            return;
        }

        String concatenatedText = Concatenator.concatenateFile(dependenciesGraph.getFilesInOrder());
        try {
            Concatenator.writeInFile(outputPath, concatenatedText);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}