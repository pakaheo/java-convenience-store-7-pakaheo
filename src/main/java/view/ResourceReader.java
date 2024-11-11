package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {

    private final List<String> resources = new ArrayList<>();

    public List<String> createResources(String path) {
        read(path);
        return resources;
    }

    private void read(String path) {
        String resourceName;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            while ((resourceName = bufferedReader.readLine()) != null) {
                resources.add(resourceName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
