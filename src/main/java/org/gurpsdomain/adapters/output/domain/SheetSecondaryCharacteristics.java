package org.gurpsdomain.adapters.output.domain;

public class SheetSecondaryCharacteristics {
    private final int will;
    private final int perception;
    private final int vision;
    private final int hearing;
    private final int tasteAndSmell;
    private final int touch;
    private final SheetBasicLift basicLift;
    private final int hitPoints;
    private final int fatiguePoints;
    private final double basicSpeed;
    private final int basicMove;
    private final int dodge;
    private final String damageThrusting;
    private final String damageSwinging;
    private final int frightCheck;
    private final int sizeModifier;

    public SheetSecondaryCharacteristics(int sizeModifier, int will, int perception, int vision, int hearing, int tasteAndSmell, int touch, SheetBasicLift basicLift, int hitPoints, int fatiguePoints, double basicSpeed, int basicMove, int dodge, String damageThrusting, String damageSwinging, int frightCheck) {
        this.sizeModifier = sizeModifier;
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
        this.dodge = dodge;
        this.damageThrusting = damageThrusting;
        this.damageSwinging = damageSwinging;
        this.frightCheck = frightCheck;
    }
}
