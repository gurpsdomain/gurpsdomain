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
        this.will = intelligence;
        this.perception = intelligence;
        this.basicLift = determineBasicLift();
        this.hitPoints = strength;
        this.fatiguePoints = health;
        this.basicSpeed = determineBasicSpeed();
        this.basicMove = determineBasicMove();
        this.damageThrusting = determineDamageThrusting();
        this.damageSwinging = determineDamageSwinging();
    }

    public void applyStrengthBonus(int bonus){strength += bonus;}
    public void applyDexterityBonus(int bonus){dexterity += bonus;}
    public void applyHealthBonus(int bonus){health += bonus;}
    public void applyIntelligenceBonus(int bonus){intelligence += bonus;}
    public void applyWillBonus(int bonus){will += bonus;}
    public void applyPerceptionBonus(int bonus){perception += bonus;}
    public void applyDamageThrustingBonus(Dice bonus){damageThrusting.addDice(bonus);}
    public void applyDamageSwingingBonus(Dice bonus){damageSwinging.addDice(bonus);}
    public void applyBasicLiftBonus(double bonus){basicLift += bonus;}
    public void applyHitPointsBonus(int bonus){hitPoints += bonus;}
    public void applyFatiguePointsBonus(int bonus){fatiguePoints += bonus;}
    public void applyBasicSpeedBonus(double bonus){basicSpeed += bonus;}
    public void applyBasicMoveBonus(int bonus){basicMove += bonus;}


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
        //ref: B15
        if (strength > 99){ return new Dice((strength / 10)+1,0);}
        return new Dice(1,1); //FIXME
    }

    private Dice determineDamageSwinging() {
        //ref: B15
        if (strength > 99){ return new Dice((strength / 10)+3,0);}
        return new Dice(1,1); //FIXME
    }

}
