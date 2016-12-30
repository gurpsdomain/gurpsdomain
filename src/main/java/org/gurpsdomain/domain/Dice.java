package org.gurpsdomain.domain;


public class Dice {
    private int number;
    private int bonus;

    public static Dice sumDice(Dice firstDice, Dice secondDice) {
        return new Dice(firstDice.number + secondDice.number, firstDice.bonus + secondDice.bonus);
    }

    Dice(int number, int bonus) {
        if (number < 0) {
            throw new IllegalArgumentException("An negative number of " + number + " dice is unexpected.");
        }
        this.number = number;
        this.bonus = bonus;
    }

    public String toString() {
        if (bonus < 0) {
            return number + "d" + bonus;
        }
        if (bonus > 0) {
            return number + "d+" + bonus;
        } else {
            return number + "d";
        }
    }
}