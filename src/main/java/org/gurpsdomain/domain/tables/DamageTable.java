package org.gurpsdomain.domain.tables;

import org.gurpsdomain.domain.Dice;

import java.util.HashMap;
import java.util.Map;

public class DamageTable {
    private static final Map<Integer, Dice> thrustingDamage = thrustingDamageTable();
    private static final Map<Integer, Dice> swingingDamage = swingingDamageTable();

    public static Dice thrustingDamageForStrength(int strengthLevel) {
        return thrustingDamage.get(strengthLevel);
    }

    public static Dice swingingDamageForStrength(int strengthLevel) {
        return swingingDamage.get(strengthLevel);
    }

    private static Map<Integer, Dice> thrustingDamageTable() {
        Map<Integer, Dice> thrustingDamageTable = new HashMap<>();
        thrustingDamageTable.put(1, new Dice(1, -6));
        thrustingDamageTable.put(2, new Dice(1, -6));
        thrustingDamageTable.put(3, new Dice(1, -5));
        thrustingDamageTable.put(4, new Dice(1, -5));
        thrustingDamageTable.put(5, new Dice(1, -4));
        thrustingDamageTable.put(6, new Dice(1, -4));
        thrustingDamageTable.put(7, new Dice(1, -3));
        thrustingDamageTable.put(8, new Dice(1, -3));
        thrustingDamageTable.put(9, new Dice(1, -2));
        thrustingDamageTable.put(10, new Dice(1, -2));
        thrustingDamageTable.put(11, new Dice(1, -1));
        thrustingDamageTable.put(12, new Dice(1, -1));
        thrustingDamageTable.put(13, new Dice(1, 0));
        thrustingDamageTable.put(14, new Dice(1, 0));
        thrustingDamageTable.put(15, new Dice(1, 1));
        thrustingDamageTable.put(16, new Dice(1, 1));
        thrustingDamageTable.put(17, new Dice(1, 2));
        thrustingDamageTable.put(18, new Dice(1, 2));
        thrustingDamageTable.put(19, new Dice(2, -1));
        thrustingDamageTable.put(20, new Dice(2, -1));
        thrustingDamageTable.put(21, new Dice(2, 0));
        thrustingDamageTable.put(22, new Dice(2, 0));
        thrustingDamageTable.put(23, new Dice(2, 1));
        thrustingDamageTable.put(24, new Dice(2, 1));
        thrustingDamageTable.put(25, new Dice(2, 2));
        thrustingDamageTable.put(26, new Dice(2, 2));
        thrustingDamageTable.put(27, new Dice(3, -1));
        thrustingDamageTable.put(28, new Dice(3, -1));
        thrustingDamageTable.put(29, new Dice(3, 0));
        thrustingDamageTable.put(30, new Dice(3, 0));
        thrustingDamageTable.put(31, new Dice(3, 1));
        thrustingDamageTable.put(32, new Dice(3, 1));
        thrustingDamageTable.put(33, new Dice(3, 2));
        thrustingDamageTable.put(34, new Dice(3, 2));
        thrustingDamageTable.put(35, new Dice(4, -1));
        thrustingDamageTable.put(36, new Dice(4, -1));
        thrustingDamageTable.put(37, new Dice(4, 0));
        thrustingDamageTable.put(38, new Dice(4, 0));
        thrustingDamageTable.put(39, new Dice(4, 1));
        thrustingDamageTable.put(40, new Dice(4, 1));
        thrustingDamageTable.put(45, new Dice(5, 0));
        thrustingDamageTable.put(50, new Dice(5, 2));
        thrustingDamageTable.put(55, new Dice(6, 0));
        thrustingDamageTable.put(60, new Dice(7, -1));
        thrustingDamageTable.put(65, new Dice(7, 1));
        thrustingDamageTable.put(70, new Dice(8, 0));
        thrustingDamageTable.put(75, new Dice(8, 2));
        thrustingDamageTable.put(80, new Dice(9, 0));
        thrustingDamageTable.put(85, new Dice(9, 2));
        thrustingDamageTable.put(90, new Dice(10, 0));
        thrustingDamageTable.put(95, new Dice(10, 2));
        thrustingDamageTable.put(100, new Dice(11, 0));
        return thrustingDamageTable;
    }

    private static Map<Integer, Dice> swingingDamageTable() {
        Map<Integer, Dice> swingingDamageTable = new HashMap<>();
        swingingDamageTable.put(1, new Dice(1, -5));
        swingingDamageTable.put(2, new Dice(1, -5));
        swingingDamageTable.put(3, new Dice(1, -4));
        swingingDamageTable.put(4, new Dice(1, -4));
        swingingDamageTable.put(5, new Dice(1, -3));
        swingingDamageTable.put(6, new Dice(1, -3));
        swingingDamageTable.put(7, new Dice(1, -2));
        swingingDamageTable.put(8, new Dice(1, -2));
        swingingDamageTable.put(9, new Dice(1, -1));
        swingingDamageTable.put(10, new Dice(1, 0));
        swingingDamageTable.put(11, new Dice(1, 1));
        swingingDamageTable.put(12, new Dice(1, 2));
        swingingDamageTable.put(13, new Dice(2, -1));
        swingingDamageTable.put(14, new Dice(2, 0));
        swingingDamageTable.put(15, new Dice(2, 1));
        swingingDamageTable.put(16, new Dice(2, 2));
        swingingDamageTable.put(17, new Dice(3, -1));
        swingingDamageTable.put(18, new Dice(3, 0));
        swingingDamageTable.put(19, new Dice(3, 1));
        swingingDamageTable.put(20, new Dice(3, 2));
        swingingDamageTable.put(21, new Dice(4, -1));
        swingingDamageTable.put(22, new Dice(4, 0));
        swingingDamageTable.put(23, new Dice(4, 1));
        swingingDamageTable.put(24, new Dice(4, 2));
        swingingDamageTable.put(25, new Dice(5, -1));
        swingingDamageTable.put(26, new Dice(5, 0));
        swingingDamageTable.put(27, new Dice(5, 1));
        swingingDamageTable.put(28, new Dice(5, 1));
        swingingDamageTable.put(29, new Dice(5, 2));
        swingingDamageTable.put(30, new Dice(5, 2));
        swingingDamageTable.put(31, new Dice(6, -1));
        swingingDamageTable.put(32, new Dice(6, -1));
        swingingDamageTable.put(33, new Dice(6, 0));
        swingingDamageTable.put(34, new Dice(6, 0));
        swingingDamageTable.put(35, new Dice(6, 1));
        swingingDamageTable.put(36, new Dice(6, 1));
        swingingDamageTable.put(37, new Dice(6, 2));
        swingingDamageTable.put(38, new Dice(6, 2));
        swingingDamageTable.put(39, new Dice(7, -1));
        swingingDamageTable.put(40, new Dice(7, -1));
        swingingDamageTable.put(45, new Dice(7, 1));
        swingingDamageTable.put(50, new Dice(8, -1));
        swingingDamageTable.put(55, new Dice(8, 1));
        swingingDamageTable.put(60, new Dice(9, 0));
        swingingDamageTable.put(65, new Dice(9, 2));
        swingingDamageTable.put(70, new Dice(10, 0));
        swingingDamageTable.put(75, new Dice(10, 2));
        swingingDamageTable.put(80, new Dice(11, 0));
        swingingDamageTable.put(85, new Dice(11, 2));
        swingingDamageTable.put(90, new Dice(12, 0));
        swingingDamageTable.put(95, new Dice(12, 2));
        swingingDamageTable.put(100, new Dice(13, 0));
        return swingingDamageTable;
    }
}
