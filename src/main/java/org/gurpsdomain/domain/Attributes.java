package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

public class Attributes {
    private final Map<Attribute, Object> bonuses = new HashMap<>();

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

    void addMageryBonus(int bonus) {
        addIntBonus(Attribute.MAGERY, bonus);
    }

    void addFrightCheckBonus(int bonus) {
        addIntBonus(Attribute.FRIGHT_CHECK, bonus);
    }

    void addPerceptionBonus(int bonus) {
        addIntBonus(Attribute.PERCEPTION, bonus);
    }

    void addVisionBonus(int bonus) {
        addIntBonus(Attribute.VISION, bonus);
    }

    void addHearingBonus(int bonus) {
        addIntBonus(Attribute.HEARING, bonus);
    }

    void addTasteAndSmellBonus(int bonus) {
        addIntBonus(Attribute.TASTE_AND_SMELL, bonus);
    }

    void addTouchBonus(int bonus) {
        addIntBonus(Attribute.TOUCH, bonus);
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

    void addDodgeBonus(int bonus) {
        addIntBonus(Attribute.DODGE, bonus);
    }

    public void addDamageThrustingBonus(Dice bonus) {
        addDiceBonus(Attribute.DAMAGE_THRUSTING, bonus);
    }

    public void addDamageSwingingBonus(Dice bonus) {
        addDiceBonus(Attribute.DAMAGE_SWINGING, bonus);
    }

    public void addBasicLiftBonus(Weight bonus) {
        addWeightBonus(Attribute.BASIC_LIFT, bonus);
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

    private void addWeightBonus(Attribute attribute, Weight aBonus) {
        Weight previous = (Weight) bonuses.getOrDefault(attribute, attribute.defaultBonus());
        Weight current = Weight.sumWeights(previous, aBonus);
        bonuses.put(attribute, current);
    }

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

    int magery() {
        return (int) value(Attribute.MAGERY);
    }

    int frightCheck() {
        return (int) value(Attribute.FRIGHT_CHECK);
    }

    int perception() {
        return (int) value(Attribute.PERCEPTION);
    }

    int vision() {
        return (int) value(Attribute.VISION);
    }

    int hearing() {
        return (int) value(Attribute.HEARING);
    }

    int tasteAndSmell() {
        return (int) value(Attribute.TASTE_AND_SMELL);
    }

    int touch() {
        return (int) value(Attribute.TOUCH);
    }

    int hitPoints() {
        return (int) value(Attribute.HIT_POINTS);
    }

    int fatiguePoints() {
        return (int) value(Attribute.FATIGUE_POINTS);
    }

    Weight basicLift() {
        return (Weight) value(Attribute.BASIC_LIFT);
    }

    String basicLiftAsStringInImperialSystem() {
        return basicLift().toStringImperialSystemNotation();
    }

    String basicLiftAsStringInRealMetricSystem() {
        return basicLift().toStringRealMetricSystemNotation();
    }

    String basicLiftAsStringInGameMetricSystem() {
        return basicLift().toStringGameMetricSystemNotation();
    }

    double basicSpeed() {
        return (double) value(Attribute.BASIC_SPEED);
    }

    int basicMove() {
        return (int) value(Attribute.BASIC_MOVE);
    }

    int dodge() {
        return (int) value(Attribute.DODGE);
    }

    Dice damageThrusting() {
        return (Dice) value(Attribute.DAMAGE_THRUSTING);
    }

    String damageThrustingAsString() {
        return damageThrusting().toString();
    }

    Dice damageSwinging() {
        return (Dice) value(Attribute.DAMAGE_SWINGING);
    }

    String damageSwingingAsString() {
        return damageSwinging().toString();
    }
}