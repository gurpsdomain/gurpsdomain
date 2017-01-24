package tools;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class YamlFromToObjectTest {
    @Test
    public void shouldLoadYamlIntoAnActualObject() throws FileNotFoundException {
        FileInputStream input = new FileInputStream(new File("src/test/resources/tools/loaded-by-yaml.yml"));
        Yaml yaml = new Yaml(new Constructor(LoadedByYaml.class));

        LoadedByYaml anObject = (LoadedByYaml) yaml.load(input);
        assertThat(anObject.name, is("Actual Object"));
        assertThat(anObject.value, is(37));
        assertThat(anObject.otherObject, is(new OtherObject(51)));
    }
}


class LoadedByYaml {
    public String name;
    public int value;
    public OtherObject otherObject;
}

class OtherObject {
    public int value;

    public OtherObject() {
    }

    public OtherObject(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) return true;
        if (anObject == null || getClass() != anObject.getClass()) return false;

        OtherObject that = (OtherObject) anObject;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}