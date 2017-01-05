package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.Spell;
import org.gurpsdomain.domain.description.SpellDescription;

import java.util.List;
import java.util.Map;

public class AddSpellsStep implements YamlBuildStep {
    private Repository<SpellDescription> repository;

    public AddSpellsStep(Repository<SpellDescription> repository) {
        this.repository = repository;
    }

    @Override
    public void build(Map<String, Object> data, SheetBuilder sheetBuilder) {
        List<Map<String, Object>> inputSpells = (List<Map<String, Object>>) data.get("spells");
        if (!(inputSpells == null)) {
            for (Map<String, Object> inputSpell : inputSpells) {
                String spellName = (String) inputSpell.get("name");
                int points = (int) inputSpell.get("points");
                if (repository.exists(spellName)) {
                    SpellDescription spellDescription = repository.getByName(spellName);

                    Spell spell = spellDescription.createSpell(points);
                    sheetBuilder.addSpell(spell);
                }
            }
        }
    }
}
