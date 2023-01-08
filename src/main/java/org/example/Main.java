package org.example;

import org.example.model.Dependency;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static String root = "/Users/ginalig/Documents/Coding/KPO";
    public static void main(String[] args) {
        DependenciesGraph dependenciesGraph = new DependenciesGraph(root);
        List<String> result = dependenciesGraph.getFilesInOrder();
        for (var res : result) {
            System.out.println(res);
        }
        //System.out.println(result);
        //dependenciesGraph.printDependenciesGraph();
    }
}