package org.gurpsdomain.domain;


public class Dice {
    private int number;
    private int adds;

    public static Dice sumDice(Dice firstDice, Dice secondDice) {
        return new Dice(firstDice.number + secondDice.number, firstDice.adds + secondDice.adds);
    }

    //FIXME this could be better
    public static Dice parseDice(String inputString) {
        int length = inputString.length();
        String diceString;
        String addsString;
        int dice;
        int adds = 0;
        if (length < 2) {
            throw new IllegalArgumentException("Dice string should have at least two characters");
        }
        if (inputString.charAt(length - 1) == 'd') {
            diceString = inputString.split("d")[0];
            dice = Integer.parseInt(diceString);
        } else {
            diceString = inputString.split("d")[0];
            addsString = inputString.split("d")[1];
            dice = Integer.parseInt(diceString);
            adds = Integer.parseInt(addsString);
        }
        return new Dice(dice, adds);
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