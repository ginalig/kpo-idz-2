package org.example;

import java.io.File;

public class Main {

    final static String root = "/Users/ginalig/Documents/Coding/KPO";
    public static void main(String[] args) {
        printAllFiles(root);
    }

    // print all directories and files in a directory
    public static void printAllFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println("Directory: " + f.getName());
                printAllFiles(f.getAbsolutePath());
            } else {
                System.out.println("File: " + f.getName());
            }
        }
    }
}