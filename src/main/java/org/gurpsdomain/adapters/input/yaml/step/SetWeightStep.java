package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;

public class SetWeightStep implements YamlBuildStep {

	@Override
	public void build(InputSheet data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "weight", data.weight());
	}
}
