package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    final static String root = "/Users/ginalig/Documents/Coding/KPO";
    final static String outputPath = "/Users/ginalig/Documents/Coding/output.txt";
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

    }
}