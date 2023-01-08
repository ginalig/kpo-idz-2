package org.example.model;

import java.util.List;

public class Dependency {
    public Dependency(String path, String text, List<Dependency> dependencies) {
        this.path = path;
        this.text = text;
        this.dependencies = dependencies;
    }

    public String path;
    public String text;
    public List<Dependency> dependencies;
}
