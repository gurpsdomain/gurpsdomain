package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.adapters.input.yaml.domain.InputSkill;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.Spell;
import org.gurpsdomain.domain.description.SpellDescription;

import java.util.List;

public class AddSpellsStep implements YamlBuildStep {
    private Repository<SpellDescription> repository;

    public AddSpellsStep(Repository<SpellDescription> repository) {
        this.repository = repository;
    }

    //FIXME spells and skills are mixed?!
    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<InputSkill> inputSpells = data.spells;
        if (!(inputSpells == null)) {
            for (InputSkill inputSpell : inputSpells) {
                String spellName = inputSpell.name;
                int points = inputSpell.points;
                if (repository.exists(spellName)) {
                    SpellDescription spellDescription = repository.getByName(spellName);

                    Spell spell = spellDescription.createSpell(points);
                    sheetBuilder.addSpell(spell);
                }
            }
        }
    }
}
