package bmo;

public class OtherBankClient extends Client {

    private String bankName;

    public OtherBankClient(String clientId, String name, String email, String phone, String bankName) {
        super(clientId, name, email, phone);
        this.bankName = bankName;
    }

    public String getBankName()         { return bankName; }
    public void   setBankName(String b) { this.bankName = b; }
}
