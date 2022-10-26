package machine;

import java.util.Scanner;

public class CoffeeMachine {

  private static final int ML_WATER_PER_CUP = 200;
  private static final int ML_MILK_PER_CUP = 50;
  private static final int G_COFFEE_PER_CUP = 15;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Write how many cups of coffee you need:");
    int numCups = scanner.nextInt();

    int mlWater = numCups * ML_WATER_PER_CUP;
    int mlMilk = numCups * ML_MILK_PER_CUP;
    int gCoffeeBeans = numCups * G_COFFEE_PER_CUP;

    System.out.printf("For %d cups of coffee you will need:%n", numCups);
    System.out.printf("%d ml of water%n", mlWater);
    System.out.printf("%d ml of milk%n", mlMilk);
    System.out.printf("%d g of coffee beans%n", gCoffeeBeans);
  }
}
