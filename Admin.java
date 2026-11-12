public class Admin extends User {

    public Admin() {
        setUserName("admin");
        setPIN("0000");
    }

    @Override
    public String menu() {

        System.out.println();
        System.out.println("Admin Menu");
        System.out.println();
        System.out.println("0) Exit");
        System.out.println("1) Full Customer Report");
        System.out.println("2) Add User");
        System.out.println("3) Apply Interest");
        System.out.println();

        System.out.print("Action (0-3): ");

        return new java.util.Scanner(System.in).nextLine();
    }

    @Override
    public void start() {
        // Admin actions are handled by the Bank class.
    }

    @Override
    public String getReport() {
        return "Admin: " + getUserName()
                + "\nPIN: " + getPIN();
    }
}
