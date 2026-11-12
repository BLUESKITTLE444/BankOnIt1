import java.io.Serializable;

public abstract class User
        implements HasMenu, Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String PIN;

    public boolean login() {

        System.out.print("User name: ");
        String username = Console.input.nextLine();

        System.out.print("PIN: ");
        String pin = Console.input.nextLine();

        return login(username, pin);
    }

    public boolean login(String userName, String PIN) {

        return this.userName.equals(userName)
                && this.PIN.equals(PIN);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getPIN() {
        return PIN;
    }

    public abstract String getReport();
}
