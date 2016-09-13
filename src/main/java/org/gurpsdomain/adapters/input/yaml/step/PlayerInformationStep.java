package org.gurpsdomain.adapters.input.yaml.step;

public class PlayerInformationStep extends CompositeStep {
    public PlayerInformationStep() {
        super(new SetPlayerStep(), new SetCampaignStep(), new SetCreatedOnStep());
    }
}
