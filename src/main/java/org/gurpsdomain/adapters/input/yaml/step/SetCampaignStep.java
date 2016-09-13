package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.SheetBuilder;

import java.util.Map;

public class SetCampaignStep implements YamlBuildStep {

	@Override
	public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
		sheetBuilder.addMetaData("player information", "campaign", (String) data.getOrDefault("campaign", ""));
	}
}