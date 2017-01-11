package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.domain.SheetBuilder;


public class SetHeightStep implements YamlBuildStep {

		@Override
		public void build(InputSheet data, SheetBuilder sheetBuilder) {
			sheetBuilder.addMetaData("description", "height", data.height());
		}
}
