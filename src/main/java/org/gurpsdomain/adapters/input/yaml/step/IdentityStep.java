package org.gurpsdomain.adapters.input.yaml.step;

public class IdentityStep extends CompositeStep {
    public IdentityStep() {
        super(new SetNameStep(), new SetTitleStep(), new SetReligionStep());
    }
}
