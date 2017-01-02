package org.gurpsdomain.domain;


public class Dice {
    private int number;
    private int adds;

    public static Dice sumDice(Dice firstDice, Dice secondDice) {
        return new Dice(firstDice.number + secondDice.number, firstDice.adds + secondDice.adds);
    }

    public Dice(int number, int adds) {
        if (number < 0) {
            throw new IllegalArgumentException("An negative number of " + number + " dice is unexpected.");
        }
        this.number = number;
        this.adds = adds;
    }

    @Override
    public String toString() {
        if (adds < 0) {
            return number + "d" + adds;
        }
        if (adds > 0) {
            return number + "d+" + adds;
        } else {
            return number + "d";
        }
    }
}