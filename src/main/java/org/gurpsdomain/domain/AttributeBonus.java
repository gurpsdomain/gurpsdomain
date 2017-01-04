package org.gurpsdomain.domain;

public class AttributeBonus {
    private final Attribute attribute;
    private final int amount;

    public AttributeBonus(Attribute attribute, int amount) {
        this.attribute = attribute;
        this.amount = amount;
    }

    public void applyTo(Attributes attributes) {
        attributes.addIntelligenceBonus(amount);
    }
}
