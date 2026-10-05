package bmo;

public class LoanAccount extends Account {

    private double interestRate;

    public LoanAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate()         { return interestRate; }
    public void   setInterestRate(double r) { this.interestRate = r; }
}
