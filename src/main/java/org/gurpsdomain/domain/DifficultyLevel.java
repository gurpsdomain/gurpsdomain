package org.gurpsdomain.domain;

import java.util.HashMap;
import java.util.Map;

public enum DifficultyLevel {
    EASY(0), AVERAGE(-1), HARD(-2), VERY_HARD(-3);

    static final private Map<String, DifficultyLevel> descriptionToLevel = new HashMap<>();
    private final int offset;

    static {
        descriptionToLevel.put("E", DifficultyLevel.EASY);
        descriptionToLevel.put("A", DifficultyLevel.AVERAGE);
        descriptionToLevel.put("H", DifficultyLevel.HARD);
        descriptionToLevel.put("VH", DifficultyLevel.VERY_HARD);
    }

    public static DifficultyLevel fromDescription(String description) {
        if (!descriptionToLevel.containsKey(description)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a DescriptionLevel key", description));
        }
        return descriptionToLevel.get(description);
    }

    DifficultyLevel(int offset) {
        this.offset = offset;
    }

    public int determineDelta(int cost) {
        if (cost < 2) {
            return offset;
        }
        if (cost < 4) {
            return 1 + offset;
        }
        return (cost / 4) + 1 + offset;
    }
}