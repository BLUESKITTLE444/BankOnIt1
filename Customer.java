import java.util.Scanner;

public class Customer extends User {

    private CheckingAccount checking;
    private SavingsAccount savings;

    private Scanner input;

    public Customer() {

        setUserName("Alice");
        setPIN("0000");

        checking = new CheckingAccount();
        savings = new SavingsAccount();

        input = new Scanner(System.in);
    }

    public Customer(String userName, String PIN) {

        setUserName(userName);
        setPIN(PIN);

        checking = new CheckingAccount();
        savings = new SavingsAccount();

        input = new Scanner(System.in);
    }

    @Override
    public String menu() {

        System.out.println();
        System.out.println("Customer Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Manage Checking Account");
        System.out.println("2) Manage Savings Account");
        System.out.println("3) Change PIN");
        System.out.println();

        System.out.print("Action (0-3): ");

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
                    System.out.println("Checking Account");
                    checking.start();
                    break;

                case "2":
                    System.out.println("Savings Account");
                    savings.start();
                    break;

                case "3":
                    changePin();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void changePin() {

        System.out.print("Enter new PIN: ");
        String newPin = input.nextLine();

        setPIN(newPin);

        System.out.println("PIN changed.");
    }

    @Override
    public String getReport() {

        return "Customer: " + getUserName()
                + "\nChecking Balance: "
                + checking.getBalanceString()
                + "\nSavings Balance: "
                + savings.getBalanceString();
    }

    public static void main(String[] args) {

        Customer customer = new Customer();

        if (customer.login()) {
            System.out.println("Login Successful");
            customer.start();
        } else {
            System.out.println("Login Failed");
        }
    }
}
