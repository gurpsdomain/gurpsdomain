package org.gurpsdomain.domain;

public class Attributes {
    private int strength;
    private int dexterity;
    private int health;
    private int intelligence;
    private int will;
    private int perception;
    private int hitPoints;
    private int fatiguePoints;
    private int basicMove;
    private double basicSpeed;
    private double basicLift;
    private Dice damageThrusting;
    private Dice damageSwinging;

    public Attributes(int aDefault) {
        this.strength = aDefault;
        this.dexterity = aDefault;
        this.health = aDefault;
        this.intelligence = aDefault;
        this.will = intelligence;
        this.perception = intelligence;
        this.hitPoints = strength;
        this.fatiguePoints = health;

        this.basicLift = basicLift();
        this.basicSpeed = basicSpeed();
        this.basicMove = basicMove();
        this.damageThrusting = damageThrusting();
        this.damageSwinging = damageSwinging();
    }

    public void applyStrengthBonus(int bonus){strength += bonus;}
    public void applyDexterityBonus(int bonus){dexterity += bonus;}
    public void applyHealthBonus(int bonus){health += bonus;}
    public void applyIntelligenceBonus(int bonus){intelligence += bonus;}
    public void applyWillBonus(int bonus){will += bonus;}
    public void applyPerceptionBonus(int bonus){perception += bonus;}
    public void applyHitPointsBonus(int bonus){hitPoints += bonus;}
    public void applyFatiguePointsBonus(int bonus){fatiguePoints += bonus;}
    public void applyBasicLiftBonus(double bonus){basicLift += bonus;}
    public void applyBasicSpeedBonus(double bonus){basicSpeed += bonus;}
    public void applyBasicMoveBonus(int bonus){basicMove += bonus;}
    public void applyDamageThrustingBonus(Dice bonus){damageThrusting.addDice(bonus);}
    public void applyDamageSwingingBonus(Dice bonus){damageSwinging.addDice(bonus);}

    //FIXME : could the enum Attribute and the class Attributes be merged? Incomplete
    public int level(Attribute attribute) {
        switch (attribute) {
            case INTELLIGENCE:
                return intelligence;
            case STRENGTH:
                return strength;
            case DEXTERITY:
                return dexterity;
            case HEALTH:
                return health;
            case WILL:
                return will;
            case PERCEPTION:
                return perception;
            default:
                return 0;
        }
    }

    private double basicLift() {
        double basicLift = (strength * strength) / 5.0;
        if (basicLift > 10) {
            return Math.round(basicLift);
        } else {
            return basicLift;
        }
    }

    private double basicSpeed() {
        return (health + dexterity) / 4.0;
    }

    private int basicMove() {
        return (int) basicSpeed;
    }

    private Dice damageThrusting() {
        //ref: B15
        if (strength > 99) {
            return new Dice((strength / 10) + 1, 0);
        }
        return new Dice(1, 1); //FIXME
    }

    private Dice damageSwinging() {
        //ref: B15
        if (strength > 99) {
            return new Dice((strength / 10) + 3, 0);
        }
        return new Dice(1, 1); //FIXME
    }

}
