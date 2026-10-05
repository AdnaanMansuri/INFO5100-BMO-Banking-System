package bmo;

public abstract class Account {

    private final String accountNumber;
    private double balance;
    private BMOClient owner;

    protected Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance()        { return balance; }
    public void   setBalance(double b){ this.balance = b; }

    public BMOClient getOwner() { return owner; }
    void setOwnerInternal(BMOClient c) { this.owner = c; }

    public boolean setOwner(BMOClient client) {
        if (client == null) {
            System.out.println("ERROR [owns]: cannot set a null owner.");
            return false;
        }
        return client.addAccount(this);
    }

    public boolean removeOwner() {
        if (owner == null) {
            System.out.println("ERROR [owns]: account " + accountNumber + " has no owner to remove.");
            return false;
        }
        return owner.removeAccount(this);
    }

    public BMOClient listOwner() { return owner; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + accountNumber + " (balance=" + balance + ")";
    }
}
