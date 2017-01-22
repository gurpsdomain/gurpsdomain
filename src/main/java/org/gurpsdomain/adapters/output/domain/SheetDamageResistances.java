package org.gurpsdomain.adapters.output.domain;

public class SheetDamageResistances {
    private final int eye;
    private final int skull;
    private final int face;
    private final int rightLeg;
    private final int rightArm;
    private final int torso;
    private final int groin;
    private final int leftArm;
    private final int leftLeg;
    private final int hand;
    private final int foot;
    private final int neck;
    private final int vitals;

    public SheetDamageResistances(int eye, int skull, int face, int rightLeg, int rightArm, int torso, int groin, int leftArm, int leftLeg, int hand, int foot, int neck, int vitals) {
        this.eye = eye;
        this.skull = skull;
        this.face = face;
        this.rightLeg = rightLeg;
        this.rightArm = rightArm;
        this.torso = torso;
        this.groin = groin;
        this.leftArm = leftArm;
        this.leftLeg = leftLeg;
        this.hand = hand;
        this.foot = foot;
        this.neck = neck;
        this.vitals = vitals;
    }
}
