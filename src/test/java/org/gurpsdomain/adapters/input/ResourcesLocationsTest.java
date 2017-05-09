package org.gurpsdomain.adapters.input;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ResourcesLocationsTest {
    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{ResourceLocations.advantagesIn("src/main/resources/data/"), Arrays.asList(String.join(File.separator, new String[]{"src", "main", "resources", "data", "advantages.basic-set.xml"}))});
        data.add(new Object[]{ResourceLocations.skillsIn("src/main/resources/data"), Arrays.asList(String.join(File.separator, new String[]{"src", "main", "resources", "data", "skills.basic-set.xml"}))});
        data.add(new Object[]{ResourceLocations.spellsIn("src/main/resources/data/"), Arrays.asList(String.join(File.separator, new String[]{"src", "main", "resources", "data", "spells.magic-set.xml"}))});
        data.add(new Object[]{ResourceLocations.equipmentIn("src/main/resources/data"), Arrays.asList(String.join(File.separator, new String[]{"src", "main", "resources", "data", "equipments.basic-set.xml"}))});
        return data;
    }

    private final ResourceLocations resourceLocations;
    private final List<String> expectedFiles;

    public ResourcesLocationsTest(ResourceLocations resourceLocations, List<String> expectedFiles) {
        this.resourceLocations = resourceLocations;
        this.expectedFiles = expectedFiles;
    }


    @Test
    public void shouldProduceCorrectFiles() {
        List<String> files = StreamSupport.stream(resourceLocations.spliterator(), false).collect(Collectors.toList());

        assertThat(files, is(expectedFiles));
    }
}

