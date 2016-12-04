package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private Map<Attribute, Object> bonus = new HashMap<Attribute, Object>();

    public Object value(Attribute attribute) {
        return attribute.value(this, bonus.getOrDefault(attribute, attribute.defaultBonus()));
    }

    private Dice damageThrusting;
    private Dice damageSwinging;

    public Attributes(int aDefault) {
        this.damageThrusting = new Dice(1, 1);
        this.damageSwinging = new Dice(1, 1);
    }

    public void applyStrengthBonus(int bonus) {
        addIntBonus(Attribute.STRENGTH, bonus);
    }

    public void applyDexterityBonus(int bonus) {
        addIntBonus(Attribute.DEXTERITY, bonus);
    }

    public void applyHealthBonus(int bonus) {
        addIntBonus(Attribute.HEALTH, bonus);
    }

    public void applyIntelligenceBonus(int bonus) {
        addIntBonus(Attribute.INTELLIGENCE, bonus);
    }

    public void applyWillBonus(int bonus) {
        addIntBonus(Attribute.WILL, bonus);
    }

    public void applyPerceptionBonus(int bonus) {
        addIntBonus(Attribute.PERCEPTION, bonus);
    }

    public void applyHitPointsBonus(int bonus) {
        addIntBonus(Attribute.HIT_POINTS, bonus);
    }

    public void applyFatiguePointsBonus(int bonus) {
        addIntBonus(Attribute.FATIGUE_POINTS, bonus);
    }

    public void applyBasicLiftBonus(double bonus) {
        addDoubleBonus(Attribute.BASIC_LIFT, bonus);
    }

    public void applyBasicSpeedBonus(double bonus) {
        addDoubleBonus(Attribute.BASIC_SPEED, bonus);
    }

    public void applyBasicMoveBonus(int bonus) {
        addIntBonus(Attribute.BASIC_MOVE, bonus);
    }

    public void applyDamageThrustingBonus(Dice bonus) {
        damageThrusting.addDice(bonus);
    }

    ;

    public void applyDamageSwingingBonus(Dice bonus) {
        damageSwinging.addDice(bonus);
    }

    private void addIntBonus(Attribute attribute, int aBonus) {
        int previous = (int) bonus.getOrDefault(attribute, attribute.defaultBonus());
        int current = previous + aBonus;
        bonus.put(attribute, current);
    }

    private void addDoubleBonus(Attribute attribute, double aBonus) {
        double previous = (double) bonus.getOrDefault(attribute, attribute.defaultBonus());
        double current = previous + aBonus;
        bonus.put(attribute, current);
    }

    private void addDiceBonus(Attribute attribute, Dice aBonus) {
        Dice previous = (Dice) bonus.getOrDefault(attribute, attribute.defaultBonus());
        previous.addDice(aBonus); /* TODO mutable dice? Big no no */
    }

    //FIXME : could the enum Attribute and the class Attributes be merged? Incomplete
    public int level(Attribute attribute) {
        return (int) value(attribute);
    }

    public int strength() {
        return (int) value(Attribute.STRENGTH);
    }

    public int dexterity() {
        return (int) value(Attribute.DEXTERITY);
    }

    public int health() {
        return (int) value(Attribute.HEALTH);
    }

    public int intelligence() {
        return (int) value(Attribute.INTELLIGENCE);
    }

    public int will() {
        return (int) value(Attribute.WILL);
    }

    public int perception() {
        return (int) value(Attribute.PERCEPTION);
    }

    public int hitPoints() {
        return (int) value(Attribute.HIT_POINTS);
    }

    public int fatiguePoints() {
        return (int) value(Attribute.FATIGUE_POINTS);
    }

    public double basicLift() {
        return (double) value(Attribute.BASIC_LIFT);
    }

    public double basicSpeed() {
        return (double) value(Attribute.BASIC_SPEED);
    }

    public int basicMove() {
        return (int) value(Attribute.BASIC_MOVE);
    }
}