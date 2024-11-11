package view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {

    public List<String> read(String path) {
        List<String> resources = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            bufferedReader.readLine();
            String resourceName;
            while ((resourceName = bufferedReader.readLine()) != null) {
                resources.add(resourceName);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resources;
    }
}
