package tools;

import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;


public class SnakeYamlTest {
    @Test
    public void shouldReturnMap() throws FileNotFoundException {
        FileInputStream input = new FileInputStream(new File("src/test/resources/tools/snake.yml"));
        Yaml yaml = new Yaml();

        Map<String, Object> data = (Map<String, Object>) yaml.load(input);

        assertThat(data, hasKey("library"));
        assertThat(data.get("library"), is("SnakeYaml"));
        assertThat(data, hasKey("usecases"));
        assertThat((List<String>) data.get("usecases"), contains("Parsing Yaml", "Dumping Yaml"));
    }

    @Test
    public void shouldProduceYamlFile() throws Throwable {
        StringWriter writer = new StringWriter();
        Yaml yaml = new Yaml();

        Map<String, Object> data = new HashMap();
        String[] usesCases = {"Parsing Yaml","Dumping Yaml"};
        data.put("library","SnakeYaml");
        data.put("usecases", usesCases);

        yaml.dump(data, writer);
        String output = writer.toString();


        BufferedReader readFile = new BufferedReader(new FileReader(new File("src/test/resources/tools/snake.yml")));
        String expectedOutput = "";
        String line;
        while((line = readFile.readLine()) != null){
            expectedOutput += line;
            expectedOutput += "\n";
        }
        assertThat(output,is(expectedOutput));
    }
}
