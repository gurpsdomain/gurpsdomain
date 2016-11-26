package org.gurpsdomain.domain;


public class Dice {
    private int number;
    private int bonus;

    public Dice(int number, int bonus) {
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

    public static Dice addDice(Dice firstDice, Dice secondDice) {
        return new Dice(firstDice.number + secondDice.number, firstDice.bonus + secondDice.bonus);
    }

    public static Dice addNumber(Dice dice, int number) {
        return new Dice(dice.number + number, dice.bonus);
    }

    public static Dice addBonus(Dice dice, int bonus) {
        return new Dice(dice.number, dice.bonus + bonus);
    }

}
