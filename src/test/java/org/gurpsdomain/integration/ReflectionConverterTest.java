package org.gurpsdomain.integration;

import org.gurpsdomain.adapters.input.SheetInput;
import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.converter.ReflectionConverter;
import org.gurpsdomain.adapters.output.domain.Sheet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static junit.framework.TestCase.assertNotNull;
import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.fromYaml;

public class ReflectionConverterTest {
    private static org.gurpsdomain.domain.Sheet sheet;

    @BeforeClass
    public static void readInSheet() throws FileNotFoundException {
        Reader reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn.yml"));
        sheet = fromYaml(reader).produce();
    }

    private SheetConverter converter;

    @Before
    public void createReflectionConverter() {
        converter = new ReflectionConverter();
    }

    @Test
    public void shouldCreateACorrectOutputSheet() {
        Sheet outputSheet = converter.convert(sheet);

        assertNotNull(outputSheet);
    }


}
