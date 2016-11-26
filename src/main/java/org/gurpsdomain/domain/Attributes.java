package org.gurpsdomain.domain;

public class Attributes {
    private int strength;
    private int dexterity;
    private int health;
    private int intelligence;
    private int will;
    private int perception;
    private Dice damageThrusting;
    private Dice damageSwinging;
    private double basicLift;
    private int hitPoints;
    private int fatiguePoints;
    private double basicSpeed;
    private int basicMove;

    public Attributes(int aDefault) {
        this.strength = aDefault;
        this.dexterity = aDefault;
        this.health = aDefault;
        this.intelligence = aDefault;
        this.will = aDefault;
        this.perception = aDefault;
        this.basicLift = determineBasicLift();
        this.hitPoints = strength;
        this.fatiguePoints = health;
        this.basicSpeed = determineBasicSpeed();
        this.basicMove = determineBasicMove();
        this.damageThrusting = determineDamageThrusting();
        this.damageSwinging = determineDamageSwinging();
    }

    private double determineBasicLift() {
        double basicLift = (strength * strength) / 5.0;
        if (basicLift > 10) {
            return Math.round(basicLift);
        } else {
            return basicLift;
        }
    }

    private double determineBasicSpeed() {
        return (health + dexterity) / 4.0;
    }

    private int determineBasicMove() {
        return (int) basicSpeed;
    }

    private Dice determineDamageThrusting() {
        return new Dice(1,1); //FIXME
    }

    private Dice determineDamageSwinging() {
        return new Dice(1,1); //FIXME
    }

}
