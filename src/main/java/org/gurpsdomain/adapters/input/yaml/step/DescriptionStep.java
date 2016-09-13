package org.gurpsdomain.adapters.input.yaml.step;

public class DescriptionStep extends CompositeStep {
    public DescriptionStep() {
        super(new SetRaceStep(), new SetGenderStep(), new SetAgeStep(), new SetBirthdayStep(),
                new SetHeightStep(), new SetWeightStep(), new SetSizeStep(), new SetTechLevelStep(),
                new SetHairStep(), new SetEyesStep(), new SetSkinStep(), new SetHandStep());
    }
}
