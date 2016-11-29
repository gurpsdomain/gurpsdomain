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

    public void addDice(Dice dice) {
        number += dice.number;
        bonus += dice.bonus;
    }

    public Dice addNumber(int number) {
        if (this.number + number < 0) {
            throw new IllegalArgumentException("Operation results in an unsupported negative number of dice");
        }
        return new Dice(this.number + number, this.bonus);
    }

    public Dice addBonus(int bonus) {
        return new Dice(this.number, this.bonus + bonus);
    }
}