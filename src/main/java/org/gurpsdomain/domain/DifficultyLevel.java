package org.gurpsdomain.domain;

public enum DifficultyLevel {
    EASY(0), AVERAGE(-1), HARD(-2), VERY_HARD(-3);

    private int offset;

    DifficultyLevel(int offset){
        this.offset = offset;
    }


    public int determineDelta(int cost) {
        if (cost < 2) {
            return 0 + offset;
        }
        if (cost < 4) {
            return 1 + offset;
        }
        return (cost / 4) + 1 + offset;
    }
}