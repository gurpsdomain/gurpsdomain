package org.gurpsdomain.domain;

public class AttributeBonus {
    private final String attribute;
    private final int amount;

    public AttributeBonus(String attribute, int amount) {
        this.attribute = attribute;
        this.amount = amount;
    }

    public void applyTo(Attributes attributes) {
        attributes.addIntelligenceBonus(amount);
    }
}
