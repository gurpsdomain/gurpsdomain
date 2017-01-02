package org.gurpsdomain.adapters.output.domain;

public class SheetSecondaryCharacteristics {
    private final int will;
    private final int perception;
    private final int vision;
    private final int hearing;
    private final int tasteAndSmell;
    private final int touch;
    private final String basicLift;
    private final int hitPoints;
    private final int fatiguePoints;
    private final double basicSpeed;
    private final int basicMove;
    private final String damageThrusting;
    private final String damageSwinging;

    public SheetSecondaryCharacteristics(int will, int perception, int vision, int hearing, int tasteAndSmell, int touch, String basicLift, int hitPoints, int fatiguePoints, double basicSpeed, int basicMove, String damageThrusting, String damageSwinging) {
        this.will = will;
        this.perception =  perception;
        this.vision = vision;
        this.hearing = hearing;
        this.tasteAndSmell = tasteAndSmell;
        this.touch = touch;
        this.basicLift = basicLift;
        this.hitPoints = hitPoints;
        this.fatiguePoints = fatiguePoints;
        this.basicSpeed = basicSpeed;
        this.basicMove = basicMove;
        this.damageThrusting = damageThrusting;
        this.damageSwinging = damageSwinging;
    }
}
