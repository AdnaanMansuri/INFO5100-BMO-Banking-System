package bmo;

public abstract class DepositAccount extends Account {

    private String nickName;

    protected DepositAccount(String accountNumber, double balance, String nickName) {
        super(accountNumber, balance);
        this.nickName = nickName;
    }

    public String getNickName()         { return nickName; }
    public void   setNickName(String n) { this.nickName = n; }
}
