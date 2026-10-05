package bmo;

public class SavingAccount extends DepositAccount {

    private double interestRate;

    public SavingAccount(String accountNumber, double balance, String nickName, double interestRate) {
        super(accountNumber, balance, nickName);
        this.interestRate = interestRate;
    }

    public double getInterestRate()         { return interestRate; }
    public void   setInterestRate(double r) { this.interestRate = r; }
}
