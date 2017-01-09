package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.adapters.input.yaml.domain.InputSkill;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.description.SkillDescription;

import java.util.List;
import java.util.Map;

public class AddSkillsStep implements YamlBuildStep {
    private Repository<SkillDescription> repository;

    public AddSkillsStep(Repository<SkillDescription> repository) {
        this.repository = repository;
    }

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<InputSkill> inputSkills = data.skills;
        if (!(inputSkills == null)) {
            for (InputSkill inputSkill : inputSkills) {
                String skillName = inputSkill.name;
                int points = (int) inputSkill.points;
                if (repository.exists(skillName)) {
                    SkillDescription skillDescription = repository.getByName(skillName);

                    Skill skill = skillDescription.createSkill(points);
                    sheetBuilder.addSkill(skill);
                }
            }
        }
    }
}
