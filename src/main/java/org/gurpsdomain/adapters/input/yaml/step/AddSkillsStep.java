package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Skill;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.SkillDescription;

import java.util.List;
import java.util.Map;

public class AddSkillsStep implements YamlBuildStep {
    private Repository<SkillDescription> repository;

    public AddSkillsStep(Repository<SkillDescription> repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Map<String, Object>> inputSkills = (List<Map<String, Object>>) data.get("skills");
        if (!(inputSkills == null)) {
            for (Map<String, Object> inputSkill : inputSkills) {
                String skillName = (String) inputSkill.get("name");
                if (repository.exists(skillName)) {
                    SkillDescription skillDescription = repository.getByName(skillName);

                    Skill skill = skillDescription.createSkill();
                    sheetBuilder.addSkill(skill);
                }
            }
        }
    }
}
