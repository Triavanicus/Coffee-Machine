package machine;

import java.util.Scanner;

public class CoffeeMachine {

  private static final Scanner scanner = new Scanner(System.in);

  private int water;
  private int milk;
  private int coffee;
  private int cups;
  private int money;

  public CoffeeMachine(int water, int milk, int coffee, int cups, int money) {
    this.water = water;
    this.milk = milk;
    this.coffee = coffee;
    this.cups = cups;
    this.money = money;
  }

  private static int inInt(String request) {
    if (request != null) {
      System.out.printf("%s:%n", request);
    }
    return scanner.nextInt();
  }

  public static void main(String[] args) {
    CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
    machine.display();
    machine.performAction(machine.requestAction());
    machine.display();
  }

  private void performAction(String action) {
    switch (action) {
      case "buy" -> buy();
      case "fill" -> fill();
      case "take" -> take();
    }
  }

  private void take() {
    System.out.printf("I gave you $%d%n%n", money);
    money = 0;
  }

  private void fill() {
    water += inInt("Write how many ml of water you want to add");
    milk += inInt("Write how many ml of milk you want to add");
    coffee += inInt("Write how many grams of coffee beans you want to add");
    cups += inInt("Write how many disposable cups you want to add");
    System.out.println();
  }

  private void buy() {
    int choice = inInt("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino");
    switch (choice) {
      case 1 -> { // espresso
        water -= 250;
        coffee -= 16;
        money += 4;
      }
      case 2 -> { // latte
        water -= 350;
        milk -= 75;
        coffee -= 20;
        money += 7;
      }
      case 3 -> { // cappuccino
        water -= 200;
        milk -= 100;
        coffee -= 12;
        money += 6;
      }
    }
    cups--;
    System.out.println();
  }

  private String requestAction() {
    System.out.println("Write action (buy, fill, take):");
    return scanner.next();
  }

  private void display() {
    System.out.println("The coffee machine has:");
    System.out.printf("%d ml of water%n", water);
    System.out.printf("%d ml of milk%n", milk);
    System.out.printf("%d g of coffee beans%n", coffee);
    System.out.printf("%d disposable cups%n", cups);
    System.out.printf("$%d of money%n", money);
    System.out.println();
  }
}
