package org.example;

import org.example.model.Dependency;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DependenciesGraph {
    private final Map<String, List<String>> dependenciesGraph = new HashMap<>();
    private final String root;

    public DependenciesGraph(String root) {
        this.root = root;
        readAllFiles(root);
    }
    private void readAllFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.isDirectory()) {
                readAllFiles(f.getAbsolutePath());
            } else {
                try {
                    addDependenciesInFile(f.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    System.out.println("kal");
                }
            }
        }
    }

    private void addDependenciesInFile(String path) throws FileNotFoundException {
        List<String> dependencies = new ArrayList<>();
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("require '")) {
                String dependency = line.substring("require '".length()).trim();
                dependencies.add(root + '/' + dependency.substring(0, dependency.length() - 1));
            }
        }
        scanner.close();
        //System.out.println(dependencies);
        dependenciesGraph.put(path, dependencies);
    }

    private void BFS(String file, List<String> dependencies, Set<String> visited, List<String> result) {
        if (visited.contains(file)) {
            return;
        }
        visited.add(file);
        for (String dependency : dependencies) {
            BFS(dependency, dependenciesGraph.get(dependency), visited, result);
        }
        result.add(file);
    }
    public List<String> getFilesInOrder() {
        Set<String> visited = new HashSet<>();
        List<String> files = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : dependenciesGraph.entrySet()) {
            String file = entry.getKey();
            List<String> fileDependencies = entry.getValue();
            BFS(file, fileDependencies, visited, files);
        }
        for (int i = 0; i < files.size(); ++i) {
            Path dirPath = Paths.get(root);
            Path filePath = Paths.get(files.get(i));
            Path relativePath = dirPath.relativize(filePath);
            files.set(i, relativePath.toString());
        }
        return files;
    }

    public void printDependenciesGraph() {
        for (Map.Entry<String, List<String>> entry : dependenciesGraph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
