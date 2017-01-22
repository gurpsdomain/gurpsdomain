package org.gurpsdomain.adapters.input.yaml.step;

import org.gurpsdomain.adapters.input.yaml.domain.InputSheet;
import org.gurpsdomain.adapters.input.yaml.domain.InputEquipment;
import org.gurpsdomain.domain.Equipment;
import org.gurpsdomain.domain.Repository;
import org.gurpsdomain.domain.SheetBuilder;
import org.gurpsdomain.domain.description.EquipmentDescription;

import java.util.List;

public class AddEquipmentsStep implements YamlBuildStep {
    private Repository<EquipmentDescription> repository;

    public AddEquipmentsStep(Repository<EquipmentDescription> repository) {
        this.repository = repository;
    }

    @Override
    public void build(InputSheet data, SheetBuilder sheetBuilder) {
        List<InputEquipment> inputEquipments = data.equipments;
        if (!(inputEquipments == null)) {
            for (InputEquipment inputEquipment : inputEquipments) {
                String equipmentName = inputEquipment.name;
                if (repository.exists(equipmentName)) {
                    EquipmentDescription equipmentDescription = repository.getByName(equipmentName);

                    Equipment equipment = equipmentDescription.createEquipment();
                    sheetBuilder.addEquipment(equipment);
                }
            }
        }
    }
}
