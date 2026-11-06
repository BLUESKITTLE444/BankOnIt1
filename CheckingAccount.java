import java.util.Scanner;

public class CheckingAccount implements HasMenu {

    private double balance;
    private Scanner input;

    public CheckingAccount() {
        balance = 0.0;
        input = new Scanner(System.in);
    }

    public CheckingAccount(double balance) {
        this.balance = balance;
        input = new Scanner(System.in);
    }

    public double getBalance() {
        return balance;
    }

    public String getBalanceString() {
        return String.format("$%.2f", balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void checkBalance() {
        System.out.println("Checking balance...");
        System.out.println("Current balance: " + getBalanceString());
    }

    private double getDouble() {
        try {
            return Double.parseDouble(input.nextLine());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public void makeDeposit() {
        System.out.println("Making a deposit...");
        System.out.print("How much to deposit? ");

        double amount = getDouble();
        balance += amount;

        System.out.println("New balance: " + getBalanceString());
    }

    public void makeWithdrawal() {
        System.out.println("Making a withdrawal...");
        System.out.print("How much to withdraw? ");

        double amount = getDouble();
        balance -= amount;

        System.out.println("New balance: " + getBalanceString());
    }

    @Override
    public String menu() {
        System.out.println();
        System.out.println("Account menu");
        System.out.println();
        System.out.println("0) quit");
        System.out.println("1) check balance");
        System.out.println("2) make a deposit");
        System.out.println("3) make a withdrawal");
        System.out.println();

        System.out.print("Please enter 0-3: ");

        return input.nextLine();
    }

    @Override
    public void start() {

        boolean done = false;

        while (!done) {

            String choice = menu();

            switch (choice) {

                case "0":
                    done = true;
                    break;

                case "1":
                    checkBalance();
                    break;

                case "2":
                    makeDeposit();
                    break;

                case "3":
                    makeWithdrawal();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        CheckingAccount acct = new CheckingAccount();
        acct.start();
    }
}
