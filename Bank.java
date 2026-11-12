import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Bank implements HasMenu {

    private Admin admin;
    private ArrayList<Customer> customerList;

    private Scanner input;
    private final String FILE_NAME = "customers.dat";

    public Bank() {

        admin = new Admin();
        customerList = new ArrayList<>();
        input = new Scanner(System.in);

        loadCustomers();

        // Initial testing data.
        // Uncomment these lines to reset the testing data.
        /*
        customerList.clear();
        customerList.add(new Customer("Alice", "0000"));
        customerList.add(new Customer("Bob", "1234"));
        saveCustomers();
        */
    }

    @Override
    public String menu() {

        System.out.println();
        System.out.println("Welcome to the Bank");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Admin Login");
        System.out.println("2) Customer Login");
        System.out.println();

        System.out.print("Action (0-2): ");

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
                    saveCustomers();
                    System.out.println("Thank you for using the bank.");
                    break;

                case "1":
                    adminLogin();
                    break;

                case "2":
                    customerLogin();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Admin login
    public void adminLogin() {

        System.out.print("Admin username: ");
        String username = input.nextLine();

        System.out.print("Admin PIN: ");
        String pin = input.nextLine();

        if (admin.login(username, pin)) {

            System.out.println("Admin Login Successful");
            adminStart();

        } else {

            System.out.println("Admin Login Failed");
        }
    }

    // Admin menu
    public void adminStart() {

        boolean done = false;

        while (!done) {

            String choice = admin.menu();

            switch (choice) {

                case "0":
                    done = true;
                    break;

                case "1":
                    fullCustomerReport();
                    break;

                case "2":
                    addUser();
                    break;

                case "3":
                    applyInterest();
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Customer login
    public void customerLogin() {

        System.out.print("User name: ");
        String username = input.nextLine();

        System.out.print("PIN: ");
        String pin = input.nextLine();

        Customer currentCustomer = null;

        for (Customer customer : customerList) {

            if (customer.login(username, pin)) {

                currentCustomer = customer;
                break;
            }
        }

        if (currentCustomer != null) {

            System.out.println("Customer Login Successful");
            currentCustomer.start();

            saveCustomers();

        } else {

            System.out.println("Customer Login Failed");
        }
    }

    // Full customer report
    public void fullCustomerReport() {

        System.out.println();
        System.out.println("Full Customer Report");
        System.out.println();

        if (customerList.isEmpty()) {
            System.out.println("No customers found.");
        }

        for (Customer customer : customerList) {

            System.out.println(customer.getReport());
            System.out.println("----------------------");
        }
    }

    // Add a new customer
    public void addUser() {

        System.out.print("Enter customer username: ");
        String username = input.nextLine();

        System.out.print("Enter customer PIN: ");
        String pin = input.nextLine();

        Customer customer = new Customer(username, pin);

        customerList.add(customer);

        System.out.println("Customer added successfully.");

        saveCustomers();
    }

    // Apply savings interest to all customers
    public void applyInterest() {

        for (Customer customer : customerList) {

            customer.getSavingsAccount().calcInterest();
        }

        System.out.println("Interest applied successfully.");

        saveCustomers();
    }

    // Save customers to file
    public void saveCustomers() {

        try {

            FileOutputStream file =
                    new FileOutputStream(FILE_NAME);

            ObjectOutputStream out =
                    new ObjectOutputStream(file);

            out.writeObject(customerList);

            out.close();
            file.close();

            System.out.println("Customer data saved.");

        } catch (IOException e) {

            System.out.println("Error saving customer data.");
            e.printStackTrace();
        }
    }

    // Load customers from file
    @SuppressWarnings("unchecked")
    public void loadCustomers() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            System.out.println("No saved customer data found.");
            return;
        }

        try {

            FileInputStream inputFile =
                    new FileInputStream(FILE_NAME);

            ObjectInputStream in =
                    new ObjectInputStream(inputFile);

            customerList =
                    (ArrayList<Customer>) in.readObject();

            in.close();
            inputFile.close();

            System.out.println("Customer data loaded.");

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading customer data.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Bank bank = new Bank();
        bank.start();
    }
}
