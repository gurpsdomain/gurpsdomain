package org.gurpsdomain.adapters.input;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceLocations implements Iterable<String> {
    public static ResourceLocations advantagesIn(String directory) {
        return new ResourceLocations(directory, and(contains("advantages"), endsWith(".xml")));
    }

    public static ResourceLocations skillsIn(String directory) {
        return new ResourceLocations(directory, and(contains("skills"), endsWith(".xml")));
    }

    public static ResourceLocations spellsIn(String directory) {
        return new ResourceLocations(directory, and(contains("spells"), endsWith(".xml")));
    }

    public static ResourceLocations equipmentIn(String directory) {
        return new ResourceLocations(directory, and(contains("equipment"), endsWith(".xml")));
    }

    public static FilenameFilter endsWith(String suffix) {
        return (dir, name) -> name.endsWith(suffix);
    }

    public static FilenameFilter contains(String part) {
        return (dir, name) -> name.contains(part);
    }

    public static FilenameFilter and(FilenameFilter left, FilenameFilter right) {
        return (dir, name) -> left.accept(dir, name) && right.accept(dir, name);
    }


    private final String directory;
    private final List<String> paths = new ArrayList<>();
    private final FilenameFilter filter;

    public ResourceLocations(String directory, FilenameFilter filter) {
        this.directory = directory;
        this.filter = filter;
        populateFiles();
    }

    private void populateFiles() {
        File directory = new File(this.directory);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not a directory"));
        }

        for (String path: directory.list(filter)) {
            paths.add(Paths.get(this.directory, path).toString());
        }
    }

    @Override
    public Iterator<String> iterator() {
        return paths.iterator();
    }
}
