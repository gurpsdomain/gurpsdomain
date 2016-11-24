package org.gurpsdomain.domain;

public class Attributes {
    private int strength;
    private int dexterity;
    private int health;
    private int intelligence;
    private int will ;
    private int perception;

    public Attributes(int aDefault){
        this.strength = aDefault;
        this.dexterity = aDefault;
        this.health = aDefault;
        this.intelligence = aDefault;
        this.will = aDefault;
        this.perception = aDefault;
    }
}
