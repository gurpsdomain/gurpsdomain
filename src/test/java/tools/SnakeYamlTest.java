package tools;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

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

}
