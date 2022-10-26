package machine;

import java.util.Scanner;

public class CoffeeMachine {

  private static final int ML_WATER_PER_CUP = 200;
  private static final int ML_MILK_PER_CUP = 50;
  private static final int G_COFFEE_PER_CUP = 15;
  private static final Scanner scanner = new Scanner(System.in);

  private static int calculateMaxCups(int water, int milk, int coffee) {
    int maxWater = water / ML_WATER_PER_CUP;
    int maxMilk = milk / ML_MILK_PER_CUP;
    int maxCoffee = coffee / G_COFFEE_PER_CUP;
    int max = maxWater;
    max = maxMilk < max ? maxMilk : max;
    max = maxCoffee < max ? maxCoffee : max;
    return max;
  }

  private static int inInt(String request) {
    if (request != null) {
      System.out.printf("%s:%n", request);
    }
    return scanner.nextInt();
  }

  public static void main(String[] args) {
    int water = inInt("Write how many ml of water the coffee machine has");
    int milk = inInt("Write how many ml of milk the coffee machine has");
    int coffee = inInt("Write how many grams of coffee beans the coffee machine has");
    int numCups = inInt("Write how many cups of coffee you need");

    int maxCups = calculateMaxCups(water, milk, coffee);
    if (numCups == maxCups) {
      System.out.println("Yes, I can make that amount of coffee");
    } else if (numCups < maxCups) {
      System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n",
          maxCups - numCups);
    } else {
      System.out.printf("No, I can make only %d cup(s) of coffee%n", maxCups);
    }
  }
}
