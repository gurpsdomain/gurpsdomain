package org.gurpsdomain.domain;

public class AttributeBonus {
    public static AttributeBonus attributeBonus(Attribute attribute, String bonus) {
        return new AttributeBonus(attribute, bonus, false);
    }

    public static AttributeBonus leveledAttributeBonus(Attribute attribute, String bonus) {
        return new AttributeBonus(attribute, bonus, true);
    }

    private final Attribute attribute;
    private final String bonus;
    private final boolean leveled;

    private AttributeBonus(Attribute attribute, String bonus, boolean leveled) {
        this.attribute = attribute;
        this.bonus = bonus;
        this.leveled = leveled;
    }

    public void applyTo(Attributes attributes) {
        attribute.addBonusTo(attributes, bonus);
    }

    public boolean isLeveled() {
        return leveled;
    }
}
