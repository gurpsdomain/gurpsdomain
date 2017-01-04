package org.gurpsdomain.domain;

public class AttributeBonus {
    private final Attribute attribute;
    private final String bonus;

    public AttributeBonus(Attribute attribute, String bonus) {
        this.attribute = attribute;
        this.bonus = bonus;
    }

    public void applyTo(Attributes attributes) {
        attribute.addBonusTo(attributes, bonus);
    }
}
