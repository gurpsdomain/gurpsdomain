package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

public class SetTechLevelStep implements YamlBuildStep {

	@Override
	public void build(InputSheet data, SheetBuilder sheetBuilder) {
		Integer techLevel = data.TL;
		String representation = techLevel != null ? techLevel.toString() : "";
		sheetBuilder.addMetaData("description", "TL", representation);
	}
}
