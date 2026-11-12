public class SavingsAccount extends CheckingAccount {

    private static final long serialVersionUID = 1L;

    private double interestRate;

    public SavingsAccount() {
        super();
        interestRate = 0.02;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        interestRate = rate;
    }

    public void calcInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
    }

    public static void main(String[] args) {

        SavingsAccount acct = new SavingsAccount();

        acct.makeDeposit();

        System.out.println("Before interest: "
                + acct.getBalanceString());

        acct.calcInterest();

        System.out.println("After interest: "
                + acct.getBalanceString());
    }
}
