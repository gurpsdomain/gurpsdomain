package org.gurpsdomain.adapters.output.domain;

public class SheetSecondaryCharacteristics {
    private final int will;
    private final int perception;
    private final double basicLift;
    private final int hitPoints;
    private final int fatiguePoints;
    private final double basicSpeed;
    private final int basicMove;
    private final String damageThrusting;
    private final String damageSwinging;

    public SheetSecondaryCharacteristics(int will, int perception, double basicLift, int hitPoints, int fatiguePoints, double basicSpeed, int basicMove, String damageThrusting, String damageSwinging) {
        this.will = will;
        this.perception =  perception;
        this.basicLift = basicLift;
        this.hitPoints = hitPoints;
        this.fatiguePoints = fatiguePoints;
        this.basicSpeed = basicSpeed;
        this.basicMove = basicMove;
        this.damageThrusting = damageThrusting;
        this.damageSwinging = damageSwinging;
    }
}
