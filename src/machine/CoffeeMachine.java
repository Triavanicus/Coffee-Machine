package machine;

import java.util.Scanner;

public class CoffeeMachine {

  private static final Scanner scanner = new Scanner(System.in);
  private int water;
  private int milk;
  private int coffee;
  private int cups;
  private int money;
  private boolean needsInput;
  private State state;
  private FillState fillState;

  public CoffeeMachine(int water, int milk, int coffee, int cups, int money) {
    this.water = water;
    this.milk = milk;
    this.coffee = coffee;
    this.cups = cups;
    this.money = money;
    this.needsInput = false;
    this.state = State.MENU;
    this.fillState = FillState.NONE;
  }


  public static void main(String[] args) {
    CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
    while (machine.run()) {
      while (machine.isRequestingInput()) {
        machine.input(scanner.nextLine());
      }
    }
  }


  private boolean run() {
    boolean running = true;
    switch (state) {
      case MENU -> showMenu();
      case BUY -> showBuyMenu();
      case FILL -> fill();
      case TAKE -> take();
      case REMAINING -> showRemaining();
      case EXIT -> running = false;
    }
    return running;
  }

  private void showMenu() {
    System.out.println("Write action (buy, fill, take, remaining, exit):");
    needsInput = true;
  }

  private void showBuyMenu() {
    System.out.println(
        "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    needsInput = true;
  }

  private void fill() {
    switch (fillState) {
      case NONE -> fillState = FillState.WATER;

      case WATER -> {
        System.out.println("Write how many ml of water you want to add:");
        needsInput = true;
      }
      case MILK -> {
        System.out.println("Write how many ml of milk you want to add:");
        needsInput = true;
      }
      case COFFEE -> {
        System.out.println("Write how many ml of coffee you want to add:");
        needsInput = true;
      }
      case CUPS -> {
        System.out.println("Write how many disposable cups you want to add:");
        needsInput = true;
      }
    }
  }

  private void take() {
    System.out.printf("I gave you $%d%n%n", money);
    money = 0;
    state = State.MENU;
  }

  private void showRemaining() {
    System.out.println("The coffee machine has:");
    System.out.printf("%d ml of water%n", water);
    System.out.printf("%d ml of milk%n", milk);
    System.out.printf("%d g of coffee beans%n", coffee);
    System.out.printf("%d disposable cups%n", cups);
    System.out.printf("$%d of money%n", money);
    System.out.println();
    state = State.MENU;
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
      System.out.printf("Sorry, not enough %s!%n", resource);
    }
  }

  private boolean isRequestingInput() {
    return needsInput;
  }

  private void input(String in) {
    switch (state) {
      case MENU -> inputMenu(in);
      case BUY -> inputBuy(in);
      case FILL -> inputFill(in);
    }
  }

  private void inputMenu(String option) {
    state = switch (option) {
      case "buy" -> State.BUY;
      case "fill" -> State.FILL;
      case "take" -> State.TAKE;
      case "remaining" -> State.REMAINING;
      case "exit" -> State.EXIT;
      default -> State.MENU;
    };
    needsInput = false;
    System.out.println();
  }

  private void inputBuy(String in) {
    switch (in) {
      case "1" -> makeCoffee(250, 0, 16, 1, 4); // espresso
      case "2" -> makeCoffee(350, 75, 20, 1, 7); // latte
      case "3" -> makeCoffee(200, 100, 12, 1, 6); // cappuccino
      case "back" -> {
      }
      default -> {
        return;
      }
    }
    needsInput = false;
    state = State.MENU;
    System.out.println();
  }

  private void inputFill(String in) {
    int num = Integer.parseInt(in);
    switch (fillState) {
      case WATER -> {
        water += num;
        fillState = FillState.MILK;
      }
      case MILK -> {
        milk += num;
        fillState = FillState.COFFEE;
      }
      case COFFEE -> {
        coffee += num;
        fillState = FillState.CUPS;
      }
      case CUPS -> {
        cups += num;
        fillState = FillState.NONE;
        state = State.MENU;
        System.out.println();
      }
    }
    needsInput = false;
  }
}