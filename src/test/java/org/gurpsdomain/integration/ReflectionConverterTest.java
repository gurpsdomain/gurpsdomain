package org.gurpsdomain.integration;

import org.gurpsdomain.adapters.input.yaml.YamlSheetInput;
import org.gurpsdomain.adapters.output.SheetConverter;
import org.gurpsdomain.adapters.output.converter.ReflectionConverter;
import org.gurpsdomain.adapters.output.domain.SheetSheet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static junit.framework.TestCase.assertNotNull;
import static org.gurpsdomain.adapters.input.yaml.YamlSheetInput.sheetInputBuilder;

public class ReflectionConverterTest {
    private static org.gurpsdomain.domain.Sheet sheet;


    @BeforeClass
    public static void readInSheet() throws FileNotFoundException {
        YamlSheetInput.Builder read = sheetInputBuilder();
        read.advantagesFrom("src/main/resources/data/advantages.basic-set.xml");
        read.skillsFrom("src/main/resources/data/skills.basic-set.xml");
        read.spellsFrom("src/main/resources/data/spells.magic-set.xml");
        read.equipmentFrom("src/main/resources/data/equipments.basic-set.xml");

        Reader reader = new FileReader(new File("src/test/resources/sheets/dai-blackthorn-input.yml"));
        sheet = read.fromYaml(reader).produce();
    }

    private SheetConverter converter;

    @Before
    public void createReflectionConverter() {
        converter = new ReflectionConverter();
    }

    @Test
    public void shouldCreateACorrectOutputSheet() {
        SheetSheet outputSheet = converter.convert(sheet);

        assertNotNull(outputSheet);
    }


}
