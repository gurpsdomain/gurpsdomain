package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

import static org.gurpsdomain.domain.DamageResistance.*;

public class DamageResistances {
    private final Map<DamageResistance, Object> bonuses = new HashMap<>();

    public Object value(DamageResistance damageResistance) {
        return damageResistance.value(this, bonuses.getOrDefault(damageResistance, damageResistance.defaultBonus()));
    }

    void addEyeBonus(int bonus) {
        addIntBonus(EYE, bonus);
    }

    void addSkullBonus(int bonus) {
        addIntBonus(SKULL, bonus);
    }

    void addFaceBonus(int bonus) {
        addIntBonus(FACE, bonus);
    }

    void addRightLegBonus(int bonus) {
        addIntBonus(RIGHT_LEG, bonus);
    }

    void addRightArmBonus(int bonus) {
        addIntBonus(RIGHT_ARM, bonus);
    }

    void addTorsoBonus(int bonus) {
        addIntBonus(TORSO, bonus);
    }

    void addGroinBonus(int bonus) {
        addIntBonus(GROIN, bonus);
    }

    void addLeftArmBonus(int bonus) {
        addIntBonus(LEFT_ARM, bonus);
    }

    void addLeftLegBonus(int bonus) {
        addIntBonus(LEFT_LEG, bonus);
    }

    void addHandBonus(int bonus) {
        addIntBonus(HAND, bonus);
    }

    void addFootBonus(int bonus) {
        addIntBonus(FOOT, bonus);
    }

    void addNeckBonus(int bonus) {
        addIntBonus(NECK, bonus);
    }

    void addVitalsBonus(int bonus) {
        addIntBonus(VITALS, bonus);
    }

    private void addIntBonus(DamageResistance damageResistance, int aBonus) {
        int previous = (int) bonuses.getOrDefault(damageResistance, damageResistance.defaultBonus());
        int current = previous + aBonus;
        bonuses.put(damageResistance, current);
    }

    public int level(DamageResistance damageResistance) {
        return (int) value(damageResistance);
    }

    int eye() {
        return (int) value(EYE);
    }

    int skull() {
        return (int) value(SKULL);
    }

    int face() {
        return (int) value(FACE);
    }

    int rightLeg() {
        return (int) value(RIGHT_LEG);
    }

    int rightArm() {
        return (int) value(RIGHT_ARM);
    }

    int torso() {
        return (int) value(TORSO);
    }

    int groin() {
        return (int) value(GROIN);
    }

    int leftArm() {
        return (int) value(LEFT_ARM);
    }

    int leftLeg() {
        return (int) value(LEFT_LEG);
    }

    int hand() {
        return (int) value(HAND);
    }

    int foot() {
        return (int) value(FOOT);
    }

    int neck() {
        return (int) value(NECK);
    }

    int vitals() {
        return (int) value(VITALS);
    }
}