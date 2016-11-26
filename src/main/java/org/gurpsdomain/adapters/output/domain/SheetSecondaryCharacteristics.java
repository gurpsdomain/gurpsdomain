package org.gurpsdomain.adapters.output.domain;

public class SheetSecondaryCharacteristics {
    private int will;
    private int perception;
    private double basicLift;
    private int hitPoints;
    private int fatiguePoints;
    private double basicSpeed;
    private int basicMove;
    private String damageThrusting;
    private String damageSwinging;

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
