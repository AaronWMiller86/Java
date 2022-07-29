import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Character {
    public static int rollSixSidedDice(){
        // Method for random number generation from 1 to 6 (six sided dice). Upper bound is max + 1.
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    public static int numberOfDice(int diceNum){
        /* Method generates a list of x number of dice rolls, sorts the list by lowest value,
        keeps the highest 3 values, and sums the remaining values.
         */
        int total = 0;
        // Loops x (diceNum) amount of times adding rollSixSidedDice() method to ArrayList diceList.
        int i = 0;
        ArrayList<Integer> diceList = new ArrayList<Integer>();
        while (i < diceNum){
            diceList.add(rollSixSidedDice());
            i +=1;
        }
        // Sorts diceList.
        Collections.sort(diceList);
        // Loops until diceList is 3 elements in length removing the smallest values each loop.
        while (diceList.size() > 3){
            diceList.remove(0);
        }
        // Loop sums the remaining 3 values and returns an int value.
        for(int j = 0; j < 3; j++){
            total += diceList.get(j);
        }

        return total;
    }

    public static String rollAbilityScores(int inputDice){
        // Method returns value of numberOfDice() for each ability and adds the modifier for the supplied score.
        int str = numberOfDice(inputDice);
        int dex = numberOfDice(inputDice);
        int con = numberOfDice(inputDice);
        int intel = numberOfDice(inputDice);
        int wis = numberOfDice(inputDice);
        int cha = numberOfDice(inputDice);

        return "Strength = " + str + " [" + abilityModifier(str) + "]" + "\n"
                + "Dexterity = " + dex + " [" + abilityModifier(dex) + "]" +  "\n"
                + "Constitution = " + con + " [" + abilityModifier(con) + "]" + "\n"
                + "Intelligence = " + intel + " [" + abilityModifier(intel) + "]" + "\n"
                + "Wisdom = " + wis + " [" + abilityModifier(wis) + "]" + "\n"
                + "Charisma = " + cha + " [" + abilityModifier(cha) + "]";
    }

    public static int abilityModifier(double inputScore){
        /* Method returns the modifier for the supplied ability score (inputScore).
        Per 5E D&D rules: scores of 8 to 9 = -1, 10 to 11 = 0, 12 to 13 = +1, etc.
        Math.floor() takes the value and rounds the value down. Double value is then converted to an int.
         */
        return (int)Math.floor((inputScore-10)/2);
    }

    public static void main(String[] args) {
        System.out.println(rollAbilityScores(4));
    }

}
