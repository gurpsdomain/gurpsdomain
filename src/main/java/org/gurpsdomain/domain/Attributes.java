package org.gurpsdomain.domain;

public class Attributes {
    private int strength;
    private int dexterity;
    private int health;
    private int intelligence;
    private int will ;
    private int perception;
//    private Dice damage;
    private double basicLift;
    private int hitPoints;
//    private int fatiguePoints;
//    private double basicSpeed;
//    private int basicMove;

    public Attributes(int aDefault){
        this.strength = aDefault;
        this.dexterity = aDefault;
        this.health = aDefault;
        this.intelligence = aDefault;
        this.will = aDefault;
        this.perception = aDefault;
        this.basicLift = determineBasicLift(strength);
        this.hitPoints = strength;
    }

    private double determineBasicLift(int strength){
        double basicLift = (strength * strength) / 5.0 ;
        if (basicLift > 10){return Math.round(basicLift);}
        else {return basicLift;}
    }
}
