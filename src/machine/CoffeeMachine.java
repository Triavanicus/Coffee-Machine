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

  private static String inString(String request) {
    if (request != null) {
      System.out.printf("%s:%n", request);
    }
    return scanner.next();
  }

  public static void main(String[] args) {
    CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
    machine.run();
  }

  private void run() {
    boolean running = true;
    while (running) {
      switch (requestAction()) {
        case "buy" -> buy();
        case "fill" -> fill();
        case "take" -> take();
        case "remaining" -> display();
        case "exit" -> running = false;
      }
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
    String choice = inString(
        "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
    switch (choice) {
      case "1" -> makeCoffee(250, 0, 16, 1, 4); // espresso
      case "2" -> makeCoffee(350, 75, 20, 1, 7); // latte
      case "3" -> makeCoffee(200, 100, 12, 1, 6); // cappuccino
      case "back" -> {
      }
    }
    System.out.println();
  }

  private void makeCoffee(int water, int milk, int coffee, int cups, int money) {
    String resource = null;
    boolean hasEnough = true;

    if (this.water < water) {
      resource = "water";
      hasEnough = false;
    } else if (this.milk < milk) {
      resource = "milk";
      hasEnough = false;
    } else if (this.coffee < coffee) {
      resource = "coffee";
      hasEnough = false;
    } else if (this.cups < cups) {
      resource = "cups";
      hasEnough = false;
    }

    if (hasEnough) {
      System.out.println("I have enough resources, making you a coffee!");
      this.water -= water;
      this.milk -= milk;
      this.coffee -= coffee;
      this.money += money;
      this.cups -= cups;
    } else {
      System.out.printf("Sorry, not enough %s%n!", resource);
    }
  }

  private String requestAction() {
    String action = inString("Write action (buy, fill, take, remaining, exit)");
    System.out.println();
    return action;
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
