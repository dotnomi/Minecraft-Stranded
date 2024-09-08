package com.dotnomi.stranded.config;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Module {
    private int id = 0;
    private String name = "null";
    private String description = "null";
    private List<Resource> neededResources = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Resource> getNeededResources() {
        return neededResources;
    }

    public void setNeededResources(List<Resource> neededResources) {
        this.neededResources = neededResources;
    }
}
