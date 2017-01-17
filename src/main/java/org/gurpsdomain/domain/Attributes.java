package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

import static org.gurpsdomain.domain.Attribute.*;

public class Attributes {
    private final Map<Attribute, Object> bonuses = new HashMap<>();

    public Object value(Attribute attribute) {
        return attribute.value(this, bonuses.getOrDefault(attribute, attribute.defaultBonus()));
    }

    void addStrengthBonus(int bonus) {
        addIntBonus(STRENGTH, bonus);
    }

    void addDexterityBonus(int bonus) {
        addIntBonus(DEXTERITY, bonus);
    }

    void addHealthBonus(int bonus) {
        addIntBonus(HEALTH, bonus);
    }

    void addIntelligenceBonus(int bonus) {
        addIntBonus(INTELLIGENCE, bonus);
    }

    void addSizeModifierBonus(int bonus) {
        addIntBonus(SIZE_MODIFIER, bonus);
    }

    void addWillBonus(int bonus) {
        addIntBonus(WILL, bonus);
    }

    void addMageryBonus(int bonus) {
        addIntBonus(MAGERY, bonus);
    }

    void addFrightCheckBonus(int bonus) {
        addIntBonus(FRIGHT_CHECK, bonus);
    }

    void addPerceptionBonus(int bonus) {
        addIntBonus(PERCEPTION, bonus);
    }

    void addVisionBonus(int bonus) {
        addIntBonus(VISION, bonus);
    }

    void addHearingBonus(int bonus) {
        addIntBonus(HEARING, bonus);
    }

    void addTasteAndSmellBonus(int bonus) {
        addIntBonus(TASTE_AND_SMELL, bonus);
    }

    void addTouchBonus(int bonus) {
        addIntBonus(TOUCH, bonus);
    }

    void addHitPointsBonus(int bonus) {
        addIntBonus(HIT_POINTS, bonus);
    }

    void addFatiguePointsBonus(int bonus) {
        addIntBonus(FATIGUE_POINTS, bonus);
    }

    void addBasicSpeedBonus(double bonus) {
        addDoubleBonus(BASIC_SPEED, bonus);
    }

    void addBasicMoveBonus(int bonus) {
        addIntBonus(BASIC_MOVE, bonus);
    }

    void addDodgeBonus(int bonus) {
        addIntBonus(DODGE, bonus);
    }

    void addDamageThrustingBonus(Dice bonus) {
        addDiceBonus(DAMAGE_THRUSTING, bonus);
    }

    void addDamageSwingingBonus(Dice bonus) {
        addDiceBonus(DAMAGE_SWINGING, bonus);
    }

    void addBasicLiftBonus(Weight bonus) {
        addWeightBonus(BASIC_LIFT, bonus);
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
        return (int) value(STRENGTH);
    }

    int dexterity() {
        return (int) value(DEXTERITY);
    }

    int health() {
        return (int) value(HEALTH);
    }

    int intelligence() {
        return (int) value(INTELLIGENCE);
    }

    int sizeModifier() {
        return (int) value(SIZE_MODIFIER);
    }

    int will() {
        return (int) value(WILL);
    }

    int magery() {
        return (int) value(MAGERY);
    }

    int frightCheck() {
        return (int) value(FRIGHT_CHECK);
    }

    int perception() {
        return (int) value(PERCEPTION);
    }

    int vision() {
        return (int) value(VISION);
    }

    int hearing() {
        return (int) value(HEARING);
    }

    int tasteAndSmell() {
        return (int) value(TASTE_AND_SMELL);
    }

    int touch() {
        return (int) value(TOUCH);
    }

    int hitPoints() {
        return (int) value(HIT_POINTS);
    }

    int fatiguePoints() {
        return (int) value(FATIGUE_POINTS);
    }

    Weight basicLift() {
        return (Weight) value(BASIC_LIFT);
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
        return (double) value(BASIC_SPEED);
    }

    int basicMove() {
        return (int) value(BASIC_MOVE);
    }

    int dodge() {
        return (int) value(DODGE);
    }

    Dice damageThrusting() {
        return (Dice) value(DAMAGE_THRUSTING);
    }

    String damageThrustingAsString() {
        return damageThrusting().toString();
    }

    Dice damageSwinging() {
        return (Dice) value(DAMAGE_SWINGING);
    }

    String damageSwingingAsString() {
        return damageSwinging().toString();
    }
}