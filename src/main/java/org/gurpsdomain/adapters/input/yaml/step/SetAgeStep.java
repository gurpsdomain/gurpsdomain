package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

public class SetAgeStep implements YamlBuildStep {

	@Override
	public void build(InputSheet data, SheetBuilder sheetBuilder) {
		Integer age = data.age;
		String representation = age != null ? age.toString(): "";
		sheetBuilder.addMetaData("description", "age", representation);
	}
}
