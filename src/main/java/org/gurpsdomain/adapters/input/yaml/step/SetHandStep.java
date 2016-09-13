package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.Map;

public class SetHandStep implements YamlBuildStep {

	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("description", "hand", (String) data.getOrDefault("hand", ""));
	}
}
