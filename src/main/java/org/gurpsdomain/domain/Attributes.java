package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private final Map<Attribute, Object> bonuses = new HashMap<>();
    private final Dice damageThrusting;
    private final Dice damageSwinging;

    public Attributes() {
        this.damageThrusting = new Dice(1, 1);
        this.damageSwinging = new Dice(1, 1);
    }

    public Object value(Attribute attribute) {
        return attribute.value(this, bonuses.getOrDefault(attribute, attribute.defaultBonus()));
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

    void addBasicSpeedBonus(double bonus) {
        addDoubleBonus(Attribute.BASIC_SPEED, bonus);
    }

    void addBasicMoveBonus(int bonus) {
        addIntBonus(Attribute.BASIC_MOVE, bonus);
    }

    public void addDamageThrustingBonus(Dice bonus) {
        addDiceBonus(Attribute.DAMAGE_THRUSTING, bonus);
    }

    public void addDamageSwingingBonus(Dice bonus) {
        addDiceBonus(Attribute.DAMAGE_SWINGING, bonus);
    }

    private void addIntBonus(Attribute attribute, int aBonus) {
        int previous = (int) bonuses.getOrDefault(attribute, attribute.defaultBonus());
        int current = previous + aBonus;
        bonuses.put(attribute, current);
    }

    private void addDoubleBonus(Attribute attribute, double aBonus) {
        double previous = (double) bonuses.getOrDefault(attribute, attribute.defaultBonus());
        double current = previous + aBonus;
        bonuses.put(attribute, current);
    }

    private void addDiceBonus(Attribute attribute, Dice aBonus) {
        Dice previous = (Dice) bonuses.getOrDefault(attribute, attribute.defaultBonus());
        Dice current = Dice.sumDice(previous, aBonus);
        bonuses.put(attribute, current);
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

    int will() {
        return (int) value(Attribute.WILL);
    }

    int perception() {
        return (int) value(Attribute.PERCEPTION);
    }

    int hitPoints() {
        return (int) value(Attribute.HIT_POINTS);
    }

    int fatiguePoints() {
        return (int) value(Attribute.FATIGUE_POINTS);
    }

    double basicLift() {
        return (double) value(Attribute.BASIC_LIFT);
    }

    double basicSpeed() {
        return (double) value(Attribute.BASIC_SPEED);
    }

    int basicMove() {
        return (int) value(Attribute.BASIC_MOVE);
    }

    Dice damageThrusting() {
        return (Dice) value(Attribute.DAMAGE_THRUSTING);
    }

    Dice damageSwinging() { return (Dice) value(Attribute.DAMAGE_SWINGING); }
}