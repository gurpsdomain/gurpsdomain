package org.gurpsdomain.adapters.input.yaml.domain;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class InputSheetTest {
    @Test
    public void shouldReadYamlIntoInputSheet() throws FileNotFoundException {
        FileInputStream input = new FileInputStream(new File("src/test/resources/sheets/dai-blackthorn-input.yml"));
        Constructor constructor = InputSheet.constructor();
        Yaml yaml = new Yaml(constructor);
        InputSheet inputSheet = (InputSheet) yaml.load(input);

        assertThat(inputSheet, is(not(nullValue())));
    }
}