package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private Map<Attribute, Object> bonus = new HashMap<>();

    public Object value(Attribute attribute) {
        return attribute.value(this, bonus.getOrDefault(attribute, attribute.defaultBonus()));
    }

    private Dice damageThrusting;
    private Dice damageSwinging;

    public Attributes() {
        this.damageThrusting = new Dice(1, 1);
        this.damageSwinging = new Dice(1, 1);
    }

     void addStrengthBonus(int bonus) {
        addIntBonus(Attribute.STRENGTH, bonus);
    }

     void addDexterityBonus(int bonus) {
        addIntBonus(Attribute.DEXTERITY, bonus);
    }

     void addHealthBonus(int bonus) {
        addIntBonus(Attribute.HEALTH, bonus);
    }

     void addIntelligenceBonus(int bonus) {
        addIntBonus(Attribute.INTELLIGENCE, bonus);
    }

     void addWillBonus(int bonus) {
        addIntBonus(Attribute.WILL, bonus);
    }

     void addPerceptionBonus(int bonus) {
        addIntBonus(Attribute.PERCEPTION, bonus);
    }

     void addHitPointsBonus(int bonus) {
        addIntBonus(Attribute.HIT_POINTS, bonus);
    }

     void addFatiguePointsBonus(int bonus) {
        addIntBonus(Attribute.FATIGUE_POINTS, bonus);
    }

     void addBasicLiftBonus(double bonus) {
        addDoubleBonus(Attribute.BASIC_LIFT, bonus);
    }

     void addBasicSpeedBonus(double bonus) {
        addDoubleBonus(Attribute.BASIC_SPEED, bonus);
    }

     void addBasicMoveBonus(int bonus) {
        addIntBonus(Attribute.BASIC_MOVE, bonus);
    }

    public void addDamageThrustingBonus(Dice bonus) {
        damageThrusting.addDice(bonus);
    }

    public void addDamageSwingingBonus(Dice bonus) {
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

    int strength() {
        return (int) value(Attribute.STRENGTH);
    }

    int dexterity() {
        return (int) value(Attribute.DEXTERITY);
    }

    int health() {
        return (int) value(Attribute.HEALTH);
    }

    int intelligence() {
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

    double basicSpeed() {
        return (double) value(Attribute.BASIC_SPEED);
    }

    int basicMove() {
        return (int) value(Attribute.BASIC_MOVE);
    }
}